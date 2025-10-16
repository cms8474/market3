package kr.co.team3.service.my;

import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.mapper.ProductOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderMapper productOrderMapper;

    public void insert(){}
    public List<ProductOrderDTO> get1Order(String u_id, String po_no){
        List<ProductOrderDTO> dtoList = productOrderMapper.selectWithU_idAndPo_no(u_id,po_no);
        return dtoList.stream().map(dto -> {
            String timestamp = dto.getPo_orderdate();
            dto.setPo_orderdate(timestamp.substring(0,10));
            return dto;
        }).toList();
    }
    public List<ProductOrderDTO> getRecent5(String u_id){
        if (u_id == null || u_id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithU_id(u_id);
        return dtoList.stream().map(dto -> {
            String timestamp = dto.getPo_orderdate();
            dto.setPo_orderdate(timestamp.substring(0,10));
            return dto;
        }).toList();
    }
    public void selectAll(){}
    public void modify(){}
    public void delete(){}
}
