package kr.co.team3.product_service;

import kr.co.team3.product_entity.CartItemsEntity;
import kr.co.team3.product_repository.CartItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartItemsRepository cartItemsRepository;

    /**
     * 장바구니에 상품 추가
     * @param ciUId 사용자 ID
     * @param ciPPid 상품 ID
     * @param ciOp01 선택한 옵션1의 POP_NO
     * @param ciOp02 선택한 옵션2의 POP_NO
     * @param ciAmount 구매 수량
     * @param ciTotPrice 총 가격
     * @return 저장된 장바구니 아이템
     */
    public CartItemsEntity addToCart(String ciUId, String ciPPid, String ciOp01, String ciOp02, 
                                   Integer ciAmount, Integer ciTotPrice) {
        
        // 기존에 같은 상품이 장바구니에 있는지 확인
        CartItemsEntity existingItem = cartItemsRepository.findByCiUIdAndCiPPid(ciUId, ciPPid);
        
        if (existingItem != null) {
            // 기존 아이템이 있으면 수량과 가격 업데이트
            existingItem.setCiAmount(existingItem.getCiAmount() + ciAmount);
            existingItem.setCiTotPrice(existingItem.getCiTotPrice() + ciTotPrice);
            return cartItemsRepository.save(existingItem);
        } else {
            // 새로운 아이템 생성
            CartItemsEntity newItem = CartItemsEntity.builder()
                    .ciUId(ciUId)
                    .ciPPid(ciPPid)
                    .ciOp01(ciOp01)
                    .ciOp02(ciOp02)
                    .ciAmount(ciAmount)
                    .ciTotPrice(ciTotPrice)
                    .build();
            return cartItemsRepository.save(newItem);
        }
    }

    /**
     * 사용자의 장바구니 목록 조회
     * @param ciUId 사용자 ID
     * @return 장바구니 목록
     */
    @Transactional(readOnly = true)
    public List<CartItemsEntity> getCartItems(String ciUId) {
        return cartItemsRepository.findByCiUId(ciUId);
    }

    /**
     * 사용자의 장바구니 목록 조회 (별칭 메서드)
     * @param userId 사용자 ID
     * @return 장바구니 목록
     */
    @Transactional(readOnly = true)
    public List<CartItemsEntity> getCartItemsByUserId(String userId) {
        return cartItemsRepository.findByCiUId(userId);
    }

    /**
     * 장바구니에서 상품 삭제
     * @param ciUId 사용자 ID
     * @param ciPPid 상품 ID
     */
    public void removeFromCart(String ciUId, String ciPPid) {
        cartItemsRepository.deleteByCiUIdAndCiPPid(ciUId, ciPPid);
    }
}
