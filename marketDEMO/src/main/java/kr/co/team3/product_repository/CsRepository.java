package kr.co.team3.product_repository;

import kr.co.team3.product_entity.CsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import java.util.Optional;

public interface CsRepository extends JpaRepository<CsEntity, String> {

    /** 타입별 조회 (NOTICE / FAQ / QNA) */
    Page<CsEntity> findByBoardTypeOrderByBoardRegDateDesc(String boardType, Pageable pageable);


    /*
     * 기본 목록 조회 (prefix로 시작하는 모든 데이터)
     */
    Page<CsEntity> findByBoardIdStartingWithOrderByBoardRegDateDesc(
            String prefix, Pageable pageable);



    /**
     * 제목 검색 (prefix + keyword)
     * 예: boardId LIKE 'noti%' AND title LIKE '%배송%'
     */
    Page<CsEntity> findByBoardIdStartingWithAndBoardTitleContainingIgnoreCase(
            String prefix, String keyword, Pageable pageable);

    @Query("""
           SELECT c
           FROM CsEntity c
           WHERE c.boardId LIKE CONCAT(:prefix, '%')
             AND (
                 :q IS NULL OR :q = '' OR
                 LOWER(c.boardTitle)   LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(c.boardContent) LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(c.boardWriter)  LIKE LOWER(CONCAT('%', :q, '%'))
             )
           ORDER BY c.boardRegDate DESC
           """)
    Page<CsEntity> searchByPrefix(@Param("prefix") String prefix,
                                  @Param("q") String q,
                                  Pageable pageable);


}
