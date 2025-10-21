package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDeliveryDetailDTO {

    // ───────────── 주문 기본정보 ─────────────
    private String poNo;              // 주문번호 (PRODUCT_ORDER.PO_NO)
    private String recipient;         // 수령인 (PRODUCT_ORDER.PO_RECIPIENT)
    private String phone;             // 연락처 (PRODUCT_ORDER.PO_HP)
    private String address;           // 배송지주소 (PRODUCT_ORDER.PO_ADDR)
    private String memo;              // 기타 요청사항 (ORDER_ITEMS.OI_ETC)

    // ───────────── 배송정보 ─────────────
    private String trackingNum;       // 송장번호 (ORDER_ITEMS.OI_TRACKING_NUM)
    private String trackingCompany;   // 택배사 (ORDER_ITEMS.OI_TRACKING_COMPANY)
    private String delStatus;         // 배송상태 (ORDER_ITEMS.OI_DEL_STATUS)

    private LocalDateTime orderDate;  // 접수일 (PRODUCT_ORDER.PO_ORDERDATE)
    private Integer itemCount;        // 상품건수 (COUNT or SUM)

    // ───────────── 금액 정보 ─────────────
    private Long totalPrice;          // 물품합계 (SUM(OI_TOT_PRICE))
    private Long deliveryPrice;       // 배송비 (PRODUCT_ORDER.PO_DELIVERY_PRICE)
    private Long sumProduct;          // 총 상품금액
    private Long sumDiscount;         // 총 할인금액
    private Long sumPay;              // 총 결제금액

    // ───────────── 상품목록 ─────────────
    private List<Item> items;         // 하위 상품 목록

    // ───────────── 내부 클래스: 상품 ─────────────
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Item {
        private String ppid;          // 상품번호 (PRODUCT_ITEM.P_PID)
        private String productName;   // 상품명 (PRODUCT_ITEM.P_NAME)
        private String sellerId;      // 판매자 ID (PRODUCT_ITEM.P_SELLER_ID)
        private Long price;           // 상품가격 (PRODUCT_ITEM.P_PRICE)
        private Integer qty;          // 수량 (ORDER_ITEMS.OI_PROD_QTY)
        private Long itemDeliveryFee; // 개별 배송비 (PRODUCT_ITEM.P_DELIVERY_PRICE)
        private Long payAmount;       // 결제금액 (ORDER_ITEMS.OI_TOT_PRICE)
        private String imageUrl;      // 대표 이미지 (PRODUCT_ITEM.P_IMAGE_MAIN)
    }
}
