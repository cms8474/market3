package kr.co.team3.product_entity;

import lombok.*;

import java.io.Serializable;

/**
 * CART_ITEMS 테이블의 복합 기본키 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItemsId implements Serializable {

    private String ciUId; // 사용자 ID
    private String ciPPid; // 상품 ID
}
