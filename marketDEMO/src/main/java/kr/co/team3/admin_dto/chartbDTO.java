package kr.co.team3.admin_dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class chartbDTO {
    private List<String> labels;
    private List<Long> values;
}
