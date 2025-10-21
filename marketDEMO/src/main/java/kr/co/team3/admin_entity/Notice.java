package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Where(clause = "B_BT_TYPE LIKE 'noti%'")
@Table(name="BOARD")
public class Notice {
    @Id
    @Column(name = "B_ID")
    private String id; // BOARD PK가 VARCHAR2라 String으로!

    @Column(name = "B_TITLE")
    private String title;

    @Column(name = "B_U_ID")
    private String writer;

    @Column(name = "B_REG_DATE")
    private LocalDateTime createdAt;

    @Column(name = "B_VIEW")
    private Integer viewCount;

    @Column(name = "B_STATE")
    private String status; // 상태값 쓰려면 BOARD의 B_STATE 사용
}