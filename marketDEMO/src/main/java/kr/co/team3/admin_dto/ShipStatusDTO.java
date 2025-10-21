package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ShipStatusDTO {
    // ORDER_ITEMS
    private String trackingNum;        // OI_TRACKING_NUM (송장번호)
    private String trackingCompany;    // OI_TRACKING_COMPANY (택배사)
    private String delStatus;          // OI_DEL_STATUS (배송상태)
    private String pPid;               // OI_P_PID (상품PK)

    // PRODUCT_ITEM
    private String productName;        // P_NAME (상품명)

    // PRODUCT_ORDER
    private String poNo;               // PO_NO (주문번호)
    private String recipient;          // PO_RECIPIENT (수령인)
    private Integer itemCount;         // PO_ITEM_COUNT (건수)
    private Long totalPrice;           // PO_TOT_PRICE (물품합계)
    private Long deliveryPrice;        // PO_DELIVERY_PRICE (배송비)
    private LocalDateTime orderDate;   // PO_ORDERDATE (접수일)
}
