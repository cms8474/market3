package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import kr.co.team3.admin_dto.RecruitmentDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_no")
    private int recruitNo;

    @Column(name = "r_dept")
    private String recruitDept;

    @Column(name = "r_career")
    private String recruitCareer;

    @Column(name = "r_type")
    private String recruitType;

    @Column(name = "r_title")
    private String recruitTitle;

    @Column(name = "r_status")
    private String recruitStatus;

    @Column(name = "r_start_date")
    private LocalDate recruitStartDate;

    @Column(name = "r_end_date")
    private LocalDate recruitEndDate;

    @CreationTimestamp
    @Column(name = "r_reg_date")
    private LocalDateTime recruitRegDate;

    /** 계산 상태 (예정/모집중/종료/미정) */
    @Transient
    public String getComputedStatus() {
        LocalDate today = LocalDate.now();
        if (recruitStartDate == null || recruitEndDate == null) return "미정";
        if (today.isBefore(recruitStartDate)) return "모집예정";
        if (!today.isAfter(recruitEndDate))   return "모집중";
        return "종료";
    }

    /** Entity → DTO */
    public RecruitmentDTO toDTO() {
        return RecruitmentDTO.builder()
                .recruitNo(recruitNo)
                .recruitDept(recruitDept)
                .recruitCareer(recruitCareer)
                .recruitType(recruitType)
                .recruitTitle(recruitTitle)
                .recruitStatus(recruitStatus)
                .recruitStartDate(recruitStartDate != null ? recruitStartDate.toString() : null) // yyyy-MM-dd
                .recruitEndDate(recruitEndDate != null ? recruitEndDate.toString() : null)       // yyyy-MM-dd
                .recruitRegDate(recruitRegDate != null ? recruitRegDate.toLocalDate().toString() : null)
                .build();
    }
}
