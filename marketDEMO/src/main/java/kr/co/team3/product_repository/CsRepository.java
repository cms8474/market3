package kr.co.team3.product_repository;

import jakarta.transaction.Transactional;
import kr.co.team3.product_entity.CsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import java.util.Optional;

public interface CsRepository extends JpaRepository<CsEntity, String> {

    /** 타입별 조회 (NOTICE / FAQ / QNA) */
    Page<CsEntity> findByBoardTypeOrderByBoardRegDateDesc(String boardType, Pageable pageable);


    /* --------- 기본 목록 --------- */
    //  noti, faq, qna 별 전체 목록
    Page<CsEntity> findByBoardIdStartingWithOrderByBoardRegDateDesc(String prefix, Pageable pageable);


    /* --------- 제목 검색 --------- */
    // 카테고리 + 제목 검색
    Page<CsEntity> findByBoardIdStartingWithAndBoardTitleContainingIgnoreCase(String prefix, String keyword, Pageable pageable);


    /* --------- 전체 검색 --------- */
    // prefix(not i, faq, qna) 기준 + 제목 키워드 검색
    @Query("SELECT c FROM CsEntity c WHERE c.boardId LIKE CONCAT(:prefix, '%') AND LOWER(c.boardTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY c.boardRegDate DESC")
    Page<CsEntity> searchByPrefix(@Param("prefix") String prefix, @Param("keyword") String keyword, Pageable pageable);


    /* --------- 조회수 증가 --------- */
    @Modifying
    @Transactional
    @Query("UPDATE CsEntity c SET c.boardView = COALESCE(c.boardView, 0) + 1 WHERE c.boardId = :id")
    void increaseView(@Param("id") String id);


    /*-- --*/

    long countByBoardIdStartingWith(String prefix);

    @Query(value = """
        SELECT LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(B_ID, INSTR(B_ID, '_', 1, 2) + 1))), 0) + 1), 4, '0') AS NEXT_NUM
        FROM BOARD
        WHERE B_BT_TYPE = :type
          AND B_U_ID = :uid
          AND B_ID LIKE :likePattern ESCAPE '\\'
        """, nativeQuery = true)
    String nextSuffix(@Param("type") String type,
                      @Param("uid") String uid,
                      @Param("likePattern") String likePattern);
}
