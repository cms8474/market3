package kr.co.team3.admin_dto;

import kr.co.team3.admin_entity.Recruitment;
import lombok.*;

import java.time.LocalDate;

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



    public Recruitment toEntity() {
        return Recruitment.builder()
                .r_no(r_no)
                .r_dept(r_dept)
                .r_career(r_career)
                .r_type(r_type)
                .r_title(r_title)
                .r_status(r_status != null ? r_status : "모집중")
                .r_start_date(parseDate(r_start_date))
                .r_end_date(parseDate(r_end_date))
                .build();
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return LocalDate.parse(dateStr);
    }

    public String getComputedStatus() {
        LocalDate today = LocalDate.now();
        LocalDate start = parseDate(r_start_date);
        LocalDate end = parseDate(r_end_date);

        if (start == null || end == null) return "미정";
        if (today.isBefore(start)) return "모집예정";
        else if (!today.isAfter(end)) return "모집중";
        else return "종료";
    }
}
