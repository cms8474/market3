package kr.co.team3.admin_dto;

import lombok.Data;

@Data
public class ProductItemDTO {
    private String pPid;          // 트리거 생성 (nullable)
    private String name;          // P_NAME
    private String desc;          // P_DESC (summary)
    private Integer price;        // P_PRICE
    private Integer discount;     // P_DISCOUNT
    private Integer point;        // P_POINT
    private Integer stockQuantity;// P_STOCK_QUANTITY
    private Integer deliveryPrice;// P_DELIVERY_PRICE
    private String sellerId;      // P_SELLER_ID
    private String pcId;          // P_PC_ID (cat2)
    private String imageList;     // P_IAMGE_LIST
    private String imageMain;     // P_IMAGE_MAIN
    private String imageDetail;   // P_IMAGE_DETAIL
    private String detailInfo;    // P_DETAIL_INFO

    private Long viewCount;   // P_VIEW_COUNT

}
