package kr.co.team3.admin_dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDTO {
    private int depositDone;
    private int canceledCnt;
    private int exchangeCnt;
    private int returnCnt;
    private int shippingCnt;

    private int orderCnt;
    private long orderAmt;

    private int signupCnt;
    private int visitCnt;
    private int boardCnt;

    private LocalDate from;
    private LocalDate to;
}
