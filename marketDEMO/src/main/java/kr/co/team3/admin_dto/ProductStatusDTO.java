package kr.co.team3.admin_dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductStatusDTO {
    private String pid;          // 상품코드 P_PID
    private String name;         // 상품명   P_NAME
    private Long salePrice;      // 판매가격 (= 할인 반영가)
    private Integer discount;    // 할인율   P_DISCOUNT (%)
    private Integer point;       // 포인트   P_POINT
    private Integer stockQty;    // 재고     P_STOCK_QUANTITY
    private String sellerName;   // 판매자   SELLER_INFO.S_COMPANY_NAME (없으면 U_USER.U_ID)
    private Long viewCount;      // 조회수   P_VIEW_COUNT
    private String imageUrl;     // 대표 이미지 (MAIN > LIST > (오타)IAMGE_LIST)
}
