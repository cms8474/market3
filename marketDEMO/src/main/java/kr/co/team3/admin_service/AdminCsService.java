package kr.co.team3.admin_service;

import kr.co.team3.product_dto.CsDTO;
import kr.co.team3.product_entity.CsEntity;
import kr.co.team3.product_repository.CsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminCsService {

    private final CsRepository csRepository;



    /** 전체 목록 */
    public Page<CsDTO> getListByPrefix(String prefix, Pageable pageable) {
        return csRepository.findByBoardIdStartingWithOrderByBoardRegDateDesc(prefix, pageable)
                .map(CsDTO::fromEntity);
    }


    /** 검색/카테고리: catePrefix가 있으면 catePrefix% 로, 없으면 prefix% 로 */
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
}