package kr.co.team3.dto.product;

import jakarta.persistence.Column;
import lombok.*;

// 강민철 2025-10-21 1703

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    @Column(name = "p_pid")
    private String pPId;
    @Column(name = "p_name")
    private String pName;
    @Column(name = "p_desc")
    private String pDesc;
    @Column(name = "p_price")
    private int pPrice;
    @Column(name = "p_discount")
    private int pDiscount;
    @Column(name = "p_point")
    private int pPoint;
    @Column(name = "p_stock_quantity")
    private int pStockQuantity;
    @Column(name = "p_delivery_price")
    private int pDeliveryPrice;
    @Column(name = "p_regdate")
    private String pRegdate;
    @Column(name = "p_seller_id")
    private String pSellerId;
    @Column(name = "p_pc_id")
    private String pPcId;
    @Column(name = "p_image_list")
    private String pImageList;
    @Column(name = "p_image_main")
    private String pImageMain;
    @Column(name = "p_image_detail")
    private String pImageDetail;
    @Column(name = "p_view_count")
    private int pViewCount;
    @Column(name = "star_avg")
    private int starAvg;
    @Column(name = "s_company_name")
    private String sCompanyName;
}
