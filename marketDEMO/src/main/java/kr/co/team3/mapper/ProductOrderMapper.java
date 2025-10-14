package kr.co.team3.mapper;

import kr.co.team3.dto.my.ProductOrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderMapper {
    public List<ProductOrderDTO> selectRecent5WithUID(@Param("po_u_id") String po_u_id);
}
