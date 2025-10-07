package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="NOTICE")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTICE_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Lob
    @Column(name = "CONTENTS", nullable = false)
    private String contents;

    @Column(name = "WRITER", length = 100)
    private String writer;

    @Column(name = "VIEW_COUNT", nullable = false)
    private Long viewCount = 0L;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status = "Y";

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}