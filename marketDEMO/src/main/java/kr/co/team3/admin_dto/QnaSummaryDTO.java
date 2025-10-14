package kr.co.team3.admin_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaSummaryDTO {
    private Long id;
    private String title;
    private String questioner;
    private LocalDateTime createdAt;
    private String answeredYn;
}