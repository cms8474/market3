package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrderDTO {

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "po_u_id")
    private String poUId;

    @Column(name = "po_pay_type")
    private String poPayType;

    @Column(name = "po_pay_detail")
    private String poPayDetail;

    @Column(name = "po_recipient")
    private String poRecipient;

    @Column(name = "po_re_phone")
    private String poRePhone;

    @Column(name = "po_item_count")
    private int poItemCount;

    @Column(name = "po_sum_price")
    private int poSumPrice;

    @Column(name = "po_discount")
    private int poDiscount;

    @Column(name = "po_delivery_price")
    private int poDeliveryPrice;

    @Column(name = "po_pri_discount")
    private int poPriDiscount;

    @Column(name = "po_tot_price")
    private int poTotPrice;

    @Column(name = "po_get_point")
    private int poGetPoint;

    @Column(name = "po_state")
    private String poState;

    @Column(name = "po_postal")
    private String poPostal;

    @Column(name = "po_base_addr")
    private String poBaseAddr;

    @Column(name = "po_detail_addr")
    private String poDetailAddr;

    @Column(name = "po_request_note")
    private String poRequestNote;

    @Column(name = "po_orderdate")
    private String poOrderdate;

    // seller_info 컬럼
    @Column(name = "s_u_id")
    private String sUId;

    @Column(name = "s_company_name")
    private String sCompanyName;

    @Column(name = "s_seller_no")
    private String sSellerNo;

    @Column(name = "s_sales_reg_num")
    private String sSalesRegNum;

    @Column(name = "s_tel")
    private String sTel;

    @Column(name = "s_fax")
    private String sFax;

    @Column(name = "s_state")
    private String sState;

    @Column(name = "s_seller_type")
    private String sSellerType;

    // order_items 컬럼
    @Column(name = "oi_prod_qty")
    private int oiProdQty;

    @Column(name = "oi_tot_price")
    private int oiTotPrice;

    @Column(name = "oi_tracking_company")
    private String oiTrackingCompany;

    @Column(name = "oi_tracking_num")
    private String oiTrackingNum;

    @Column(name = "oi_etc")
    private String oiEtc;

    @Column(name = "oi_del_status")
    private String oiDelStatus;

    // product_item 컬럼
    @Column(name = "p_pid")
    private String pPid;

    @Column(name = "p_name")
    private String pName;

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
}
