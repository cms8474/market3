package kr.co.team3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "POINT_HISTORY")
public class PointHistory {

    @Id
    @Column(name = "UH_NO")
    private String uhNo;

    @Column(name = "UH_U_ID")
    private String uhUId;

    @Column(name = "UH_PO_NO")
    private String uhPoNo;

    @Column(name = "UH_TEXT")
    private String uhText;

    @Column(name = "UH_CHANGE_POINT")
    private Integer uhChangePoint;

    @Column(name = "UH_CHANGE_DATE")
    private Integer uhChangeDate;

    @Column(name = "UH_DDAY")
    private LocalDateTime uhDday;
}
