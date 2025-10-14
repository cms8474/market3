package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private Date r_start_date;
    private Date  r_end_date;

    @CreationTimestamp
    private LocalDateTime r_reg_date;

}
