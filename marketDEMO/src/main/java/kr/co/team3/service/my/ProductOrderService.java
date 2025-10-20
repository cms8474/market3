package kr.co.team3.service.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PageResponseDTO;
import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.mapper.my.ProductOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 강민철 2025-10-20 1457

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderMapper productOrderMapper;

    public void insert(){}
    public List<ProductOrderDTO> getOrder1(String u_id, String po_no){
        List<ProductOrderDTO> dtoList = productOrderMapper.selectWithU_idAndPo_no(u_id,po_no);
        return dtoList.stream().map(dto -> {
            String timestamp = dto.getPoOrderdate();
            dto.setPoOrderdate(timestamp.substring(0,10));
            return dto;
        }).toList();
    }
    public List<ProductOrderDTO> getRecent5(String u_id){
        if (u_id == null || u_id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithU_id(u_id);
        return dtoList.stream().map(dto -> {
            String timestamp = dto.getPoOrderdate();
            dto.setPoOrderdate(timestamp.substring(0,10));
            return dto;
        }).toList();
    }
    public int getCountOrder(String u_id, PageRequestDTO pageRequestDTO){
        if (u_id == null || u_id.isEmpty()) {
            return 0;
        }
        return productOrderMapper.selectCountOrder(u_id, pageRequestDTO);
    }
    public PageResponseDTO selectAll(String uId, PageRequestDTO pageRequestDTO){
        List<ProductOrderDTO> dtoList = productOrderMapper.selectAll(uId, pageRequestDTO);
        dtoList.forEach(dto -> {
            String formatted =  dto.getPoOrderdate().substring(0, 10);
            dto.setPoOrderdate(formatted.substring(0,10));
        });

        int total = productOrderMapper.selectCountOrder(uId, pageRequestDTO);

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .poDtoList(dtoList)
                .total(total)
                .build();
    }
    public void modify(){}
    public void delete(){}
}
