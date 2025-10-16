package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import kr.co.team3.admin_dto.RecruitmentDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RECRUITMENT")
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int r_no;

    private String r_dept;
    private String r_career;

    private String r_type;
    private String r_title;
    private String r_status;

    private LocalDate r_start_date;
    private LocalDate  r_end_date;

    @CreationTimestamp
    private LocalDateTime r_reg_date;


    public RecruitmentDTO toDTO() {
        return RecruitmentDTO.builder()
                .r_no(r_no)
                .r_dept(r_dept)
                .r_career(r_career)
                .r_type(r_type)
                .r_title(r_title)
                .r_status(r_status)
                // LocalDate → String 변환
                .r_start_date(r_start_date != null ? r_start_date.toString() : null)
                .r_end_date(r_end_date != null ? r_end_date.toString() : null)
                // LocalDateTime → String 변환 (날짜만)
                .r_reg_date(r_reg_date != null ? r_reg_date.toLocalDate().toString() : null)
                .build();
    }

    @Transient
    public String getComputedStatus() {
        LocalDate today = LocalDate.now();

        LocalDate start = r_start_date;
        LocalDate end   = r_end_date;

        if (start == null || end == null) return "미정";
        if (today.isBefore(start)) return "모집예정";
        if (!today.isAfter(end))   return "모집중";
        return "종료";
    }

}
