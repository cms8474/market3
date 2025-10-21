package kr.co.team3.product_repository;

import kr.co.team3.product_entity.ProductReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, String> {
    
    /**
     * 특정 상품의 리뷰를 페이지네이션으로 조회
     * @param prPPid 상품 ID
     * @param pageable 페이지네이션 정보
     * @return 리뷰 페이지
     */
    @Query("SELECT pr FROM ProductReviewEntity pr WHERE pr.prPPid = :prPPid ORDER BY pr.prRegDate DESC")
    Page<ProductReviewEntity> findByPrPPidOrderByPrRegDateDesc(@Param("prPPid") String prPPid, Pageable pageable);
    
    /**
     * 특정 상품의 리뷰 개수 조회
     * @param prPPid 상품 ID
     * @return 리뷰 개수
     */
    @Query("SELECT COUNT(pr) FROM ProductReviewEntity pr WHERE pr.prPPid = :prPPid")
    long countByPrPPid(@Param("prPPid") String prPPid);
}
