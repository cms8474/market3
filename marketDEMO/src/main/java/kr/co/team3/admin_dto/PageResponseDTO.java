package kr.co.team3.admin_dto;

import kr.co.team3.product_dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponseDTO {


    private List<MemberDTO> members;



    private String cate;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private String searchType;
    private String keyword;

    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<MemberDTO> members, int total){

        this.members = members;


        this.cate = pageRequestDTO.getCate();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;


        this.startNo = total - ((pg - 1) * size);
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;



        int last = (int) (Math.ceil(total / (double) size));
        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

        this.searchType = pageRequestDTO.getSearchType();
        this.keyword = pageRequestDTO.getKeyword();


    }

}
