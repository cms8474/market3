package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "QNA") // 실제 테이블명과 맞추기
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QNA_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Lob
    @Column(name = "QUESTION", nullable = false)
    private String question;

    @Column(name = "QUESTIONER", length = 100)
    private String questioner;

    @Lob
    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWERED_YN", nullable = false, length = 1)
    private String answeredYn = "N";

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status = "Y";

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ANSWERED_AT")
    private LocalDateTime answeredAt;
}