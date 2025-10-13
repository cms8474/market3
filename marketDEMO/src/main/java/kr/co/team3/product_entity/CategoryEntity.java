package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품 카테고리 (PRODUCT_CATEGORY)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT_CATEGORY")
public class CategoryEntity {

    @Id
    @Column(name = "PC_ID", length = 10)
    private String pcId; // 카테고리 ID

    @Column(name = "PC_NAME", length = 50)
    private String pcName; // 카테고리 이름

    @Column(name = "PC_LEVER", length = 10)
    private String pcLever; // 레벨(1차, 2차 등)
}
