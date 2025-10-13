package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * PRODUCT_ITEM 기반 상품 정보 Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT_ITEM")
public class IndexEntity {

    @Id
    @Column(name = "P_PID")
    private String pPid;

    @Column(name = "P_NAME")
    private String pName;

    @Column(name = "P_PRICE")
    private int pPrice;

    @Column(name = "P_DISCOUNT")
    private int pDiscount;

    @Column(name = "P_VIEW_COUNT")
    private int pViewCount;

    @Lob
    @Column(name = "P_IMAGE_MAIN")
    private byte[] pImageMain;

    @Column(name = "P_REGDATE")
    private java.time.LocalDateTime pRegdate;
}
