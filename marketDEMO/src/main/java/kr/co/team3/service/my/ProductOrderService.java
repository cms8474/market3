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
    public void select(){}
    public List<ProductOrderDTO> selectRecent5WithUID(String po_u_id){
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithUID(po_u_id);
        dtoList.stream().map(dto -> {
            String timestamp = dto.getPo_orderdate();
            dto.setPo_orderdate(timestamp.substring(0,10));
            return dto;
        }).toList();
        return dtoList;
    }
    public void selectAll(){}
    public void modify(){}
    public void delete(){}
}
