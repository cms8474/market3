package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.ProductStatusDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductStatusMapper {


    List<ProductStatusDTO> selectProductStatusPage(@Param("req") PageRequestDTO req);

    int countProductStatus(@Param("req") PageRequestDTO req);

    // 단건
    int deleteCartItems(@Param("pid") String pid);
    int deleteCategoryLinks(@Param("pid") String pid);
    int deleteOrderItems(@Param("pid") String pid);
    int deleteProductDetail(@Param("pid") String pid);
    int deleteProductOptions(@Param("pid") String pid);
    int deleteProductReview(@Param("pid") String pid);
    int deleteTagsLinks(@Param("pid") String pid);
    int deleteProduct(@Param("pid") String pid);

    // 다건
    int deleteCartItemsBulk(@Param("pids") List<String> pids);
    int deleteCategoryLinksBulk(@Param("pids") List<String> pids);
    int deleteOrderItemsBulk(@Param("pids") List<String> pids);
    int deleteProductDetailBulk(@Param("pids") List<String> pids);
    int deleteProductOptionsBulk(@Param("pids") List<String> pids);
    int deleteProductReviewBulk(@Param("pids") List<String> pids);
    int deleteTagsLinksBulk(@Param("pids") List<String> pids);
    int deleteProducts(@Param("pids") List<String> pids);
}