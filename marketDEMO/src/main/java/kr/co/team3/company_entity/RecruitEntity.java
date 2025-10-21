package kr.co.team3.company_entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecruitEntity {
    private int rNo;
    private String rDept;
    private String rCareer;
    private String rType;
    private String rTitle;
    private String rStatus;
    private LocalDateTime rStartDate;
    private LocalDateTime rEndDate;
    private LocalDateTime rRegDate;
}
