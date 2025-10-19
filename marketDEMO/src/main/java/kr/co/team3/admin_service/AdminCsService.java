package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.product_dto.CsDTO;
import kr.co.team3.product_entity.CsEntity;
import kr.co.team3.product_repository.CsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCsService {

    private final CsRepository csRepository;


    /*전체 목록*/
    public Page<CsDTO> getListByPrefix(String prefix, Pageable pageable) {
        return csRepository.findByBoardIdStartingWithOrderByBoardRegDateDesc(prefix, pageable)
                .map(CsDTO::fromEntity);
    }


    /*검색/카테고리: catePrefix가 있으면 catePrefix% 로, 없으면 prefix% 로*/
    public Page<CsDTO> searchByPrefix(String prefix, String catePrefix, String q, Pageable pageable) {
        if (catePrefix == null || catePrefix.isBlank()) {
            // 전체(not i% / faq% / qna%) + 통합검색
            return csRepository.searchByPrefix(prefix, q, pageable).map(CsDTO::fromEntity);
        }
        // 특정 카테고리(not i01%, faq20% 등)
        if (q != null && !q.isBlank()) {
            return csRepository.findByBoardIdStartingWithAndBoardTitleContainingIgnoreCase(catePrefix, q, pageable)
                    .map(CsDTO::fromEntity);
        } else {
            return csRepository.findByBoardIdStartingWithOrderByBoardRegDateDesc(catePrefix, pageable)
                    .map(CsDTO::fromEntity);
        }
    }

    @Transactional
    public CsDTO getDetail(String id) {
        csRepository.increaseView(id);
        return csRepository.findById(id).map(CsDTO::fromEntity).orElse(null);
    }


    /*공지사할 저장*/
    @Transactional
    public String save(CsDTO dto) {
        // boardId 생성
        String prefix = dto.getBoardType().trim().toLowerCase();
        String writer = dto.getBoardWriter().trim().toLowerCase();
        String pattern = prefix + "_" + writer + "\\_%";


        String suffix = csRepository.nextSuffix(prefix, writer, pattern); // e.g. "0002"
        if (suffix == null || suffix.isBlank()) suffix = "0001";

        String bid = prefix + "_" + writer + "_" + suffix;
        dto.setBoardId(bid);                // ← 트리거와 동일 규칙으로 선반영
        dto.setBoardView(0);


        try {
            CsEntity saved = csRepository.save(dto.toEntity());
            return saved.getBoardId();
        } catch (DataIntegrityViolationException e) {
            //  동시 PK 충돌 시 1회 재시도
            String retrySuffix = csRepository.nextSuffix(prefix, writer, pattern);
            if (retrySuffix == null || retrySuffix.isBlank()) retrySuffix = "0001";
            String retryBid = prefix + "_" + writer + "_" + retrySuffix;
            dto.setBoardId(retryBid);
            CsEntity saved = csRepository.save(dto.toEntity());

            log.info(" [SAVE NOTICE] boardWriter = {}", dto.getBoardWriter());
            log.info(" [SAVE NOTICE] bid = {}", dto.getBoardId());
            return saved.getBoardId();
        }

    }


    @Transactional
    public String saveByType(CsDTO dto) {
        //  필수값 검증
        if (dto.getBoardType() == null || dto.getBoardType().isBlank()) {
            throw new IllegalArgumentException("boardType is required");
        }
        if (dto.getBoardWriter() == null || dto.getBoardWriter().isBlank()) {
            dto.setBoardWriter("admin01"); // 기본값
        }

        final String prefix = dto.getBoardType().trim();   // ex) noti01 / faq01 / qna01
        final String writer = dto.getBoardWriter().trim(); // ex) admin01
        final String pattern = prefix + "_" + writer + "\\_%";

        //  다음 suffix 조회
        String suffix = csRepository.nextSuffix(prefix, writer, pattern);
        if (suffix == null || suffix.isBlank()) suffix = "0001";

        String bid = prefix + "_" + writer + "_" + suffix;
        dto.setBoardId(bid);
        if (dto.getBoardView() == null) dto.setBoardView(0);

        log.info(" [SAVE BOARD] try BID={}, type={}, writer={}", bid, prefix, writer);

        try {
            CsEntity saved = csRepository.save(dto.toEntity());
            log.info(" [SAVE BOARD] saved BID={}", saved.getBoardId());
            return saved.getBoardId();

        } catch (DataIntegrityViolationException ex) {
            // 동시 PK 충돌 시 재시도
            log.warn("PK conflict on BID={}, retrying once...", bid);
            try { Thread.sleep(30L); } catch (InterruptedException ignored) {}

            String retrySuffix = csRepository.nextSuffix(prefix, writer, pattern);
            if (retrySuffix == null || retrySuffix.isBlank()) {
                try {
                    int n = Integer.parseInt(suffix);
                    retrySuffix = String.format("%04d", n + 1);
                } catch (NumberFormatException e2) {
                    retrySuffix = "0001";
                }
            }
            String retryBid = prefix + "_" + writer + "_" + retrySuffix;
            dto.setBoardId(retryBid);
            CsEntity saved = csRepository.save(dto.toEntity());
            log.info(" [SAVE BOARD] saved BID={}", saved.getBoardId());
            return saved.getBoardId();
        }
    }


    //수정
    @Transactional
    public void update(CsDTO dto) {
        CsEntity entity = csRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + dto.getBoardId()));

        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContent(dto.getBoardContent());
        entity.setBoardType(dto.getBoardType());

        log.info(" [UPDATE] {} 수정 완료", dto.getBoardId());
    }


    //qna답변등록
    @Transactional
    public void updateAnswer(CsDTO dto) {
        CsEntity entity = csRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + dto.getBoardId()));

        entity.setBoardAnswer(dto.getBoardAnswer());
        entity.setBoardState("답변완료"); // 예: 답변완료 상태로 변경
        entity.setBoardListener(dto.getBoardListener());

        log.info("🗨 답변 등록 완료 -> {} ({})", entity.getBoardId(), entity.getBoardListener());
    }


    //삭제
    @Transactional
    public void delete(String boardId) {
        csRepository.deleteById(boardId);
    }


    @Transactional
    public void deleteAllByIds(List<String> ids) {
        csRepository.deleteAllById(ids);
    }



}