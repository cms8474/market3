package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDetailDTO {

    // 공통/결제
    private String  poNo;         // 주문번호
    private String  payType;      // 결제수단(po_pay_type)
    private String  state;        // 주문상태(po_state)
    private Integer sumPrice;     // 상품합계(po_sum_price)
    private Integer discount;     // 총할인(po_discount)
    private Integer deliveryPrice;// 배송비(po_delivery_price)
    private Integer totalPrice;   // 총결제(po_tot_price)
    private LocalDateTime orderDate;

    // 주문자
    private String userId;        // po_u_id
    private String userName;      // u_user.u_name
    private String userPhone;     // u_user.u_phone

    // 배송
    private String recipient;     // po_recipient
    private String rePhone;       // po_re_phone
    private String baseAddr;      // po_base_addr
    private String detailAddr;    // po_detail_addr

    // 상품목록
    private List<OrderItemDTO> items;

    private String courier;      // OI_TRACKING_COMPANY
    private String trackingNum;  // OI_TRACKING_NUM
    private String etc;          // OI_ETC

    // --- 상품 목록 ---

    @Getter @Setter @ToString
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrderItemDTO {
        private String productPid;     // oi_p_pid
        private String productName;    // p_item.p_name
        private String sellerId;       // oi_s_u_id
        private String sellerName;     // seller_info.s_company_name
        private Integer price;         // p_item.p_price (정가)
        private Integer discount;      // p_item.p_discount
        private Integer quantity;      // oi_prod_qty
        private Integer deliveryFee;   // p_item.p_delivery_price
        private Integer itemTotalPrice;// oi_tot_price (해당 행 결제금액)
    }
}
