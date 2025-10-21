package kr.co.team3.admin_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter

@AllArgsConstructor
public class NoticeSummaryDTO {
    private String id;
    private String title;

    private LocalDateTime createdAt;


}
