package kr.co.team3.product_repository;

import kr.co.team3.product_entity.CartItemsEntity;
import kr.co.team3.product_entity.CartItemsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItemsEntity, CartItemsId> {

    /**
     * 특정 사용자의 장바구니 목록 조회
     * @param ciUId 사용자 ID
     * @return 장바구니 목록
     */
    @Query("SELECT ci FROM CartItemsEntity ci WHERE ci.ciUId = :ciUId")
    List<CartItemsEntity> findByCiUId(@Param("ciUId") String ciUId);

    /**
     * 특정 사용자의 특정 상품이 장바구니에 있는지 확인
     * @param ciUId 사용자 ID
     * @param ciPPid 상품 ID
     * @return 장바구니 아이템
     */
    @Query("SELECT ci FROM CartItemsEntity ci WHERE ci.ciUId = :ciUId AND ci.ciPPid = :ciPPid")
    CartItemsEntity findByCiUIdAndCiPPid(@Param("ciUId") String ciUId, @Param("ciPPid") String ciPPid);

    /**
     * 특정 사용자의 장바구니 아이템 삭제
     * @param ciUId 사용자 ID
     * @param ciPPid 상품 ID
     */
    void deleteByCiUIdAndCiPPid(String ciUId, String ciPPid);
}
