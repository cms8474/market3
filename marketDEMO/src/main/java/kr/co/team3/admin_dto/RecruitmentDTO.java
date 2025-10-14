package kr.co.team3.admin_dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDTO {

    private int r_no;
    private String r_dept;
    private String r_career;

    private String r_type;
    private String r_title;
    private String r_status;

    private String r_start_date;
    private String r_end_date;
    private String r_reg_date;

    public String getRstartdate() {
        return r_start_date.substring(2,16);
    }

}
