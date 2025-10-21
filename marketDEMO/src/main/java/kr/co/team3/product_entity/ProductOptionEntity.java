package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품 옵션 정보 (PRODUCT_OPTION)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT_OPTION")
public class ProductOptionEntity {

    @Id
    @Column(name = "POP_NO", length = 50)
    private String popNo; // 옵션 번호 (PK)

    @Column(name = "POP_P_PID", length = 50)
    private String popPPid; // 상품 ID (FK)

    @Column(name = "POP_NAME", length = 50)
    private String popName; // 옵션명 (예: 색상, 사이즈)

    @Column(name = "POP_SELECTION", length = 50)
    private String popSelection; // 옵션 선택값 (예: 블랙, 화이트)

    @Column(name = "POP_ADD_PRICE")
    private Integer popAddPrice; // 추가 가격

    @Column(name = "POP_STOCK")
    private Integer popStock; // 재고
}
