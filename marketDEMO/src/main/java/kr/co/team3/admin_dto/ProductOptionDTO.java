package kr.co.team3.admin_dto;

import lombok.Data;

@Data
public class ProductOptionDTO {
    private String pPid;      // FK
    private String name;      // POP_NAME
    private String selection; // POP_SELECTION (콤마 split)
    private Integer addPrice; // POP_ADD_PRICE (기본 0)
    private Integer stock;    // POP_STOCK (기본 999)
}
