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
    private String poNo;              // 주문번호
    private String recipient;         // 수령인
    private String phone;             // 연락처
    private String address;           // 배송지주소
    private String memo;              // 기타 요청사항

    private String trackingNum;       // 송장번호
    private String trackingCompany;   // 택배사
    private String delStatus;         // 배송상태

    private LocalDateTime orderDate;  // 접수일
    private Integer itemCount;        // 상품건수

    // ───────────── 금액 정보 ─────────────
    private Long totalPrice;          // 물품합계
    private Long deliveryPrice;       // 배송비
    private Long sumProduct;          // 총 상품금액
    private Long sumDiscount;         // 총 할인금액
    private Long sumPay;              // 총 결제금액

    // ───────────── 상품목록 ─────────────
    private List<Item> items;         // 하위 클래스 리스트

    // ───────────── 내부 클래스: 상품 ─────────────
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Item {
        private String pPid;          // 상품번호
        private String productName;   // 상품명
        private String sellerId;      // 판매자ID
        private Long price;           // 상품가격
        private Integer qty;          // 수량
        private Long itemDeliveryFee; // 개별 배송비
        private Long payAmount;       // 결제금액
        private String imageUrl;      // 대표 이미지
    }
}
