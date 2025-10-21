package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.ProductDetailDTO;
import kr.co.team3.admin_dto.ProductItemDTO;
import kr.co.team3.admin_dto.ProductOptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminProductMapper {

    int insertProductItem(ProductItemDTO dto);

    int insertProductDetail(ProductDetailDTO dto);

    int insertProductOption(ProductOptionDTO dto);

    String selectLastPidBySeller(@Param("sellerId") String sellerId);

    ProductItemDTO selectProductByPid(@Param("pid") String pid);

    List<ProductOptionDTO> selectOptionsByPid(@Param("pid") String pid);
}
