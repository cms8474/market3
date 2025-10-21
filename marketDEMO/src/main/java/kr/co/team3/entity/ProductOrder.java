package kr.co.team3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {

    @Id
    @Column(name = "PO_NO")
    private String poNo;

    @Column(name = "PO_U_ID")
    private String poUId;

    @Column(name = "PO_PAY_TYPE")
    private String poPayType;

    @Column(name = "PO_PAY_DETAIL")
    private String poPayDetail;

    @Column(name = "PO_RECIPIENT")
    private String poRecipient;

    @Column(name = "PO_RE_PHONE")
    private String poRePhone;

    @Column(name = "PO_ITEM_COUNT")
    private Integer poItemCount;

    @Column(name = "PO_SUM_PRICE")
    private Integer poSumPrice;

    @Column(name = "PO_DISCOUNT")
    private Integer poDiscount;

    @Column(name = "PO_DELIVERY_PRICE")
    private Integer poDeliveryPrice;

    @Column(name = "PO_PRI_DISCOUNT")
    private Integer poPriDiscount;

    @Column(name = "PO_TOT_PRICE")
    private Integer poTotPrice;

    @Column(name = "PO_GET_POINT")
    private Integer poGetPoint;

    @Column(name = "PO_STATE")
    private String poState;

    @Column(name = "PO_POSTAL")
    private String poPostal;

    @Column(name = "PO_BASE_ADDR")
    private String poBaseAddr;

    @Column(name = "PO_DETAIL_ADDR")
    private String poDetailAddr;

    @Column(name = "PO_REQUEST_NOTE")
    private String poRequestNote;

    @Column(name = "PO_ORDERDATE")
    private LocalDateTime poOrderdate;

    @PrePersist
    protected void onCreate() {
        // DB 트리거가 PO_NO를 생성하므로 여기서는 아무것도 하지 않음
        // 단지 JPA가 persist를 허용하도록 함
    }
}
