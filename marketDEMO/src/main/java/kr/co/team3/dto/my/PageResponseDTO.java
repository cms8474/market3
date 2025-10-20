package kr.co.team3.dto.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

// 강민철 2025-10-20 1457

@Data
@AllArgsConstructor
public class PageResponseDTO {

    private List<ProductOrderDTO> dtoList;

    private String cate;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private String unit;
    private String recentMonths;
    private String startDate;
    private String endDate;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<ProductOrderDTO> dtoList, int total){
        this.cate = pageRequestDTO.getCate();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.startNo = total - ((pg - 1) * size);
        this.end = (int) Math.ceil(this.pg / 10.0) * 10;
        this.start = this.end - 9;

        int last = (int) Math.ceil(total / (double) size);
        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

        this.unit = pageRequestDTO.getUnit();
        this.recentMonths = pageRequestDTO.getRecentMonths();
        this.startDate = pageRequestDTO.getStartDate();
        this.endDate = pageRequestDTO.getEndDate();
    }
}
