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
    public ProductOrderDTO get1OrderItem(String u_id, String po_no, String p_pid){
        return productOrderMapper.selectWithU_idAndPo_noAndP_pid(u_id,po_no,p_pid);
    }
    public List<ProductOrderDTO> getRecent5(String u_id){
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
