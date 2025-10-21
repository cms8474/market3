package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD") // 실제 테이블명과 맞추기
@Where(clause = "B_BT_TYPE LIKE 'qna%'")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Qna {
    @Id
    @Column(name = "B_ID")
    private String id;

    @Column(name = "B_TITLE")
    private String title;

    @Column(name = "B_U_ID")
    private String questioner; // 템플릿에서 쓰는 필드명

    @Column(name = "B_REG_DATE")
    private LocalDateTime createdAt;

    @Column(name = "B_VIEW")
    private Integer viewCount;

    @Column(name = "B_STATE")
    private String status;
}