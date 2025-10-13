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
    private String po_request_not;
    private String po_orderdate;
}
