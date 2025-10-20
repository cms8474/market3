package kr.co.team3.mapper.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.ProductOrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 강민철 2025-10-20 1710

@Mapper
public interface ProductOrderMapper {
    public List<ProductOrderDTO> selectRecent5WithU_id(@Param("u_id") String u_id);
    public List<ProductOrderDTO> selectWithU_idAndPo_no(@Param("u_id") String u_id,
                                                                @Param("po_no") String po_no);
    public int selectCountOrder(@Param("u_id") String uId, @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    public List<ProductOrderDTO> selectAll(@Param("u_id") String u_id, @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    public int updatePoState(@Param("uId") String uId, @Param("poNo") String poNo);
}
