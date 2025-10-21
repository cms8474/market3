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

    @Column(name = "P_DESC")
    private String pDesc;

    @Column(name = "P_PRICE")
    private int pPrice;

    @Column(name = "P_DISCOUNT")
    private int pDiscount;

    @Column(name = "P_POINT")
    private Integer pPoint;

    @Column(name = "P_STOCK_QUANTITY")
    private Integer pStockQuantity;

    @Column(name = "P_DELIVERY_PRICE")
    private Integer pDeliveryPrice;

    @Column(name = "P_REGDATE")
    private java.time.LocalDateTime pRegdate;

    @Column(name = "P_SELLER_ID")
    private String pSellerId;

    @Column(name = "P_PC_ID")
    private String pPcId;

    @Column(name = "P_IAMGE_LIST")
    private String pImageList;

    @Column(name = "P_IMAGE_MAIN")
    private String pImageMain;

    @Column(name = "P_IMAGE_DETAIL")
    private String pImageDetail;

    @Column(name = "P_DETAIL_INFO")
    private String pDetailInfo;

    @Column(name = "P_VIEW_COUNT")
    private Integer pViewCount;
}
