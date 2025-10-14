package kr.co.team3.product_repository;

import kr.co.team3.product_entity.CsEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsRepository extends JpaRepository<CsEntity, String> {

    /** 타입별 조회 (NOTICE / FAQ / QNA) */
    Page<CsEntity> findByBoardTypeOrderByBoardRegDateDesc(String boardType, Pageable pageable);
}
