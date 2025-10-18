package kr.co.team3.admin_dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistoryListDTO {
    private String uh_no;
    private String u_id;
    private String u_name;
    private Integer uh_change_point;
    private Integer uh_now_point;
    private String uh_text;
    private LocalDate uh_change_date;   // 지급 날짜
}
