package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * PRODUCT_DETAIL 테이블 기반 상품 상세 정보 Entity
 */
@Entity
@Table(name = "PRODUCT_DETAIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailEntity {

    @Id
    @Column(name = "PD_P_PID", length = 50)
    private String pdPid; // 상품 ID (FK → PRODUCT_ITEM.P_PID)

    @Column(name = "PD_STATE", length = 50)
    private String pdState; // 상품상태

    @Column(name = "PD_TAX", length = 50)
    private String pdTax; // 부가세면세여부

    @Column(name = "PD_RECEIPT", length = 100)
    private String pdReceipt; // 영수증발행여부

    @Column(name = "PD_S_SELLER_TYPE", length = 50)
    private String pdSSellerType; // 사업자구분

    @Column(name = "PD_BRAND", length = 50)
    private String pdBrand; // 브랜드

    @Column(name = "PD_ORIGIN", length = 50)
    private String pdOrigin; // 원산지

    @Column(name = "PD_MAKER", length = 100)
    private String pdMaker; // 제조자/수입국

    @Column(name = "PD_MANUF_COUNTRY", length = 50)
    private String pdManufCountry; // 제조국

    @Column(name = "PD_CARE", length = 200)
    private String pdCare; // 취급시 주의사항

    @Column(name = "PD_MANUF_DATE", length = 50)
    private String pdManufDate; // 제조연월

    @Column(name = "PD_WARRANTY_INFO", length = 200)
    private String pdWarrantyInfo; // 품질보증기준

    @Column(name = "PD_AS", length = 100)
    private String pdAs; // A/S 책임자

    @Column(name = "PD_PHONE", length = 50)
    private String pdPhone; // 전화번호
}
