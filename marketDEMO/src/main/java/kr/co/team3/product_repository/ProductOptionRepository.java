package kr.co.team3.product_repository;

import kr.co.team3.product_entity.ProductOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 상품 옵션 Repository
 */
@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, String> {

    /**
     * 상품 ID로 옵션 목록 조회
     */
    @Query("SELECT p FROM ProductOptionEntity p WHERE p.popPPid = :pPid ORDER BY p.popName, p.popSelection")
    List<ProductOptionEntity> findByPopPPid(@Param("pPid") String pPid);

    /**
     * 상품 ID와 옵션명으로 옵션 목록 조회
     */
    @Query("SELECT p FROM ProductOptionEntity p WHERE p.popPPid = :pPid AND p.popName = :popName ORDER BY p.popSelection")
    List<ProductOptionEntity> findByPopPPidAndPopName(@Param("pPid") String pPid, @Param("popName") String popName);
}
