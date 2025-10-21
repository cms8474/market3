package kr.co.team3.mapper.product;

import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 강민철 2025-10-21 1703

@Mapper
public interface ProductMapper {
    public List<ProductDTO> selectWithKeyword(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    public int countWithKeyword(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
}
