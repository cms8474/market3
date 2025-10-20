package kr.co.team3.dto.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// 강민철 2025-10-20 1457

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    private int no = 1;

    @Builder.Default
    private int pg = 1;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private String cate = "free";

    @Builder.Default
    private String unit = null;
    @Builder.Default
    private String recentMonths = null;
    @Builder.Default
    private String startDate = null;
    @Builder.Default
    private String endDate = null;

    private int offset;

    public int getOffset() {
        return (pg - 1) * size;
    }

    public Pageable getPageable(String sort){
        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());
    }
}
