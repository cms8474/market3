package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.product_dto.MemberDTO;
import kr.co.team3.product_mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberService {

    public PageResponseDTO selectAll(PageRequestDTO pageRequestDTO) {

        List<MemberDTO> dtoList = MemberMapper.selectAll(pageRequestDTO);

        int total = MemberMapper.selectCountTotal(pageRequestDTO);

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public void modify(MemberDTO memberDTO){

    }



    public void delete(int uId){


    }
}
