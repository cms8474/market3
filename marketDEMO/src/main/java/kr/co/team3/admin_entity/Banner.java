package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "AD_INFO", schema = "MARKET_DEV")
@SequenceGenerator(
        name = "ad_info_seq",
        sequenceName = "MARKET_DEV.AD_INFO_A_NO_SEQ",
        allocationSize = 1
)
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_info_seq")
    @Column(name = "A_NO")
    private Long aNo;

    @Column(name = "A_NAME", length = 50, nullable = true)
    private String aName;

    @Column(name = "A_SIZE", length = 50)
    private String aSize; // "1200 x 80" 같은 원문 저장

    @Column(name = "A_BACK_COLOR", length = 50)
    private String aBackColor; // #ffffff 등

    @Column(name = "A_LINK", length = 200)
    private String aLink;

    @Column(name = "A_LOCATION", length = 20)
    private String aLocation;

    @Column(name = "A_START_DATE")
    private LocalDate aStartDate;

    @Column(name = "A_END_DATE")
    private LocalDate aEndDate;

    @Column(name = "A_START_TIME")
    private LocalDateTime aStartTime;

    @Column(name = "A_END_TIME")
    private LocalDateTime aEndTime;

    @Column(name = "A_FILE", length = 100)
    private String aFile;

    @Column(name = "A_REG_DATE")
    private OffsetDateTime aRegDate;
}
