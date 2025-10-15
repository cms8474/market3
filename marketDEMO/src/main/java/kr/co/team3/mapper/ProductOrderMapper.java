package kr.co.team3.mapper;

import kr.co.team3.dto.my.ProductOrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderMapper {
    public List<ProductOrderDTO> selectRecent5WithU_id(@Param("u_id") String u_id);
    public ProductOrderDTO selectWithU_idAndPo_noAndP_pid(@Param("u_id") String u_id,
                                                                @Param("po_no") String po_no,
                                                                @Param("p_pid") String p_pid);
}
