package kr.co.team3.admin_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter

@AllArgsConstructor
public class NoticeSummaryDTO {
    private Long id;
    private String title;
    private String writer;
    private LocalDateTime createdAt;


}
