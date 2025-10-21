package kr.co.team3.admin_dto;

import lombok.Data;

@Data
public class ProductDetailDTO {
    private String pPid;       // FK
    private String state;      // PD_STATE
    private String tax;        // PD_TAX
    private String receipt;    // PD_RECEIPT
    private String sellerType; // PD_S_SELLER_TYPE
    private String brand;      // PD_BRAND
    private String origin;     // PD_ORIGIN
    private String maker;      // PD_MAKER
    private String country;    // PD_MANUF_COUNTRY
    private String care;       // PD_CARE
    private String manufDate;  // PD_MANUF_DATE
    private String warranty;   // PD_WARRANTY_INFO
    private String asManager;  // PD_AS
    private String phone;      // PD_PHONE
}
