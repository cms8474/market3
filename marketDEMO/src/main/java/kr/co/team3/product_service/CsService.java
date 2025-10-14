package kr.co.team3.product_service;

import kr.co.team3.product_dto.CsDTO;
import kr.co.team3.product_entity.CsEntity;
import kr.co.team3.product_repository.CsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CsService {

    private final CsRepository csRepository;

    /** 최신 N건 조회 */
    @Transactional(readOnly = true)
    public List<CsDTO> getLatest(String boardType, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "boardRegDate"));
        return csRepository.findByBoardTypeOrderByBoardRegDateDesc(boardType, pageable)
                .getContent()
                .stream()
                .map(CsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /** 목록 조회 (페이징) */
    @Transactional(readOnly = true)
    public Page<CsDTO> getList(String boardType, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), size, Sort.by(Sort.Direction.DESC, "boardRegDate"));
        return csRepository.findByBoardTypeOrderByBoardRegDateDesc(boardType, pageable)
                .map(CsDTO::fromEntity);
    }

    /** 상세 조회 */
    @Transactional(readOnly = true)
    public CsDTO getDetail(String boardId) {
        CsEntity entity = csRepository.findById(boardId).orElse(null);
        if (entity == null) return null;

        if ("QNA".equalsIgnoreCase(entity.getBoardType())) {
            entity.setBoardState(
                    (entity.getBoardAnswer() == null || entity.getBoardAnswer().isBlank())
                            ? "검토 중" : "답변 완료"
            );
        }
        return CsDTO.fromEntity(entity);
    }

    /** 등록 (문의/QNA 작성) */
    public String write(CsDTO dto) {
        CsEntity entity = dto.toEntity();
        if (entity.getBoardRegDate() == null) entity.setBoardRegDate(LocalDateTime.now());
        if ("QNA".equalsIgnoreCase(entity.getBoardType()) &&
                (entity.getBoardState() == null || entity.getBoardState().isBlank())) {
            entity.setBoardState("검토 중");
        }
        return csRepository.save(entity).getBoardId();
    }
}
