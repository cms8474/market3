package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {

    private List<T> dtoList;

    private String cate;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private String searchType;
    private String keyword;

    // (추가) 매출현황 전용 필드
    private String sort;          // sales_sum | order_sum
    private String dir;           // asc | desc
    private String periodType;    // day | week | month
    private LocalDate baseDate;   // 기준일

    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<T> dtoList, int total) {
        this.cate = pageRequestDTO.getCate();
        this.pg = Math.max(pageRequestDTO.getPg(), 1);
        this.size = Math.max(pageRequestDTO.getSize(), 1);
        this.total = Math.max(total, 0);
        this.dtoList = dtoList;

        // 역순 시작 번호 (총 개수 - (현재 페이지-1)*사이즈)
        this.startNo = this.total - ((this.pg - 1) * this.size);

        // 페이지 블록 계산 (10개 단위)
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) Math.ceil(this.total / (double) this.size);
        if (last == 0) {
            this.start = 1;
            this.end = 1;
        } else {
            this.end = Math.min(this.end, last);
            this.start = Math.max(this.start, 1);
        }

        this.prev = this.start > 1;
        this.next = this.total > this.end * this.size;

        // 검색
        this.searchType = pageRequestDTO.getSearchType();
        this.keyword = pageRequestDTO.getKeyword();

        // (추가된 매출현황 필드)
        this.sort = pageRequestDTO.getSort();
        this.dir = pageRequestDTO.getDir();
        this.periodType = pageRequestDTO.getPeriodType();
        this.baseDate = pageRequestDTO.getBaseDate();
    }
}
