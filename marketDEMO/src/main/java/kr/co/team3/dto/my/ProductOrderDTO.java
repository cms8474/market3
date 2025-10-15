package kr.co.team3.dto.my;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrderDTO {
    private String po_no;
    private String po_u_id;
    private String po_pay_type;
    private String po_pay_detail;
    private String po_recipient;
    private String po_re_phone;
    private int po_item_count;
    private int po_sum_price;
    private int po_discount;
    private int po_delivery_price;
    private int po_pri_discount;
    private int po_tot_price;
    private int po_get_point;
    private String po_state;
    private String po_postal;
    private String po_base_addr;
    private String po_detail_addr;
    private String po_request_note;
    private String po_orderdate;

    // seller_info 컬럼
    private String s_u_id;
    private String s_company_name;
    private String s_seller_no;
    private String s_sales_reg_num;
    private String s_tel;
    private String s_fax;
    private String s_state;
    private String s_seller_type;

    // order_items 컬럼
    private int oi_prod_qty;
    private int oi_tot_price;
    private String oi_tracking_company;
    private String oi_tracking_num;
    private String oi_etc;
    private String oi_del_status;

    // product_item 컬럼
    private String p_pid;
    private String p_name;
    //private String p_desc;
    private int p_price;
    private int p_discount;
    private int p_point;
    private int p_stock_quantity;
    private int p_delivery_price;
    //private String p_regdate;
    //private String p_seller_id;
    //private String p_pc_id;
}
