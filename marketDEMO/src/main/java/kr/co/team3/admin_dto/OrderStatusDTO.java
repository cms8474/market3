package kr.co.team3.admin_dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderStatusDTO {
    private String poNo;
    private String poUId;
    private String uName;
    private int itemCount;
    private int sumPrice;
    private String payType;
    private String state;
    private LocalDateTime orderDate;

    private String shipStatus; // ← 추가 (입력완료 표시용)
}
