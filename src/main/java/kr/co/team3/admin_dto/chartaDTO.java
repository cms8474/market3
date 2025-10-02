package kr.co.team3.admin_dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class chartaDTO {
    private List<String> charts;
    private List<Long> orders;
    private List<Long> payments;
    private List<Long> cancels;
}
