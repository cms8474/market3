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

    private int recruitNo;
    private String recruitDept;
    private String recruitCareer;

    private String recruitType;
    private String recruitTitle;
    private String recruitStatus;

    /** 폼에서 받는 yyyy-MM-dd 문자열 */
    private String recruitStartDate;
    private String recruitEndDate;
    private String recruitRegDate; // 표시용 날짜 문자열

    /** DTO → Entity */
    public Recruitment toEntity() {
        return Recruitment.builder()
                .recruitNo(recruitNo)
                .recruitDept(recruitDept)
                .recruitCareer(recruitCareer)
                .recruitType(recruitType)
                .recruitTitle(recruitTitle)
                .recruitStatus(recruitStatus != null ? recruitStatus : "모집중")
                .recruitStartDate(parseDate(recruitStartDate))
                .recruitEndDate(parseDate(recruitEndDate))
                .build();
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return LocalDate.parse(dateStr); // yyyy-MM-dd
    }

    /** 계산 상태 (DTO 측에서도 필요하면 사용) */
    public String getComputedStatus() {
        LocalDate today = LocalDate.now();
        LocalDate start = parseDate(recruitStartDate);
        LocalDate end   = parseDate(recruitEndDate);
        if (start == null || end == null) return "미정";
        if (today.isBefore(start)) return "모집예정";
        if (!today.isAfter(end))   return "모집중";
        return "종료";
    }
}
