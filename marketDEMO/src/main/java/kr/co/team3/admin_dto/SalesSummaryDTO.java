package kr.co.team3.admin_dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesSummaryDTO {

    /* 판매자 ID */
    private String sellerUid;

    /* 상호명 (SELLER_INFO.S_COMPANY_NAME) */
    private String companyName;

    /* 사업자등록번호 (SELLER_INFO.S_SALES_REG_NUM) */
    private String bizRegNum;

    /* 총 주문 건수 */
    private Long totalOrders;

    /* 결제완료된 주문 건수  */
    private Long paidCount;

    /*배송중인 건수  */
    private Long shippingCount;

    /* 배송완료된 건수  */
    private Long deliveredCount;

    /*구매확정된 건수  */
    private Long confirmedCount;

    /* 주문합계 */
    private BigDecimal orderTotal;

    /* 매출합계 */
    private BigDecimal salesTotal;
}
