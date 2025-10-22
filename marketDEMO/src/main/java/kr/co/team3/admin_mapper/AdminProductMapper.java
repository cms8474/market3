package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.ProductDetailDTO;
import kr.co.team3.admin_dto.ProductItemDTO;
import kr.co.team3.admin_dto.ProductOptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminProductMapper {

    // 등록 관련
    int insertProductItem(ProductItemDTO dto);
    int insertProductDetail(ProductDetailDTO dto);
    int insertProductOption(ProductOptionDTO dto);

    String selectLastPidBySeller(@Param("sellerId") String sellerId);

    // 조회 관련
    ProductItemDTO selectProductByPid(@Param("pid") String pid);
    ProductDetailDTO selectDetailByPid(@Param("pid") String pid);
    List<ProductOptionDTO> selectOptionsByPid(@Param("pid") String pid);

    // 수정/삭제 관련
    void updateProductItem(ProductItemDTO item);
    void upsertProductDetail(ProductDetailDTO detail);
    void deleteOptionsByPid(@Param("pid") String pid);
}
