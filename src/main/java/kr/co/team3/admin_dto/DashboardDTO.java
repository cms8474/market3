package kr.co.team3.admin_dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDTO {
    private long waiting;
    private long ready;
    private long cancel;
    private long exchange;
    private long returnA;
    private long orderCount;
    private long orderAmount;
    private long registerCount;
    private long visitor;
    private long qna;
}
