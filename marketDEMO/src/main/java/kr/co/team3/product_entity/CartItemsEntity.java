package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * CART_ITEMS 테이블 기반 장바구니 Entity
 */
@Entity
@Table(name = "CART_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(CartItemsId.class)
public class CartItemsEntity {

    @Id
    @Column(name = "CI_U_ID", length = 20)
    private String ciUId; // 사용자 ID (FK → U_USER.U_ID)

    @Id
    @Column(name = "CI_P_PID", length = 50)
    private String ciPPid; // 상품 ID (FK → PRODUCT_ITEM.P_PID)

    @Column(name = "CI_OP01", length = 100)
    private String ciOp01; // 선택한 옵션1의 POP_NO

    @Column(name = "CI_OP02", length = 100)
    private String ciOp02; // 선택한 옵션2의 POP_NO

    @Column(name = "CI_AMOUNT")
    private Integer ciAmount; // 구매 수량

    @Column(name = "CI_TOT_PRICE")
    private Integer ciTotPrice; // 총 가격
}
