package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.ProductStatusDTO;
import kr.co.team3.admin_mapper.CouponIssueMapper;
import kr.co.team3.admin_mapper.ProductStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStatusService {

    private final ProductStatusMapper productStatusMapper;
    private final CouponIssueMapper couponIssueMapper;

    public PageResponseDTO<ProductStatusDTO> getPage(PageRequestDTO req) {
        // 페이지/사이즈 기본값 방어
        if (req.getPg() < 1) req.setPg(1);
        if (req.getSize() < 1) req.setSize(10);

        List<ProductStatusDTO> list = productStatusMapper.selectProductStatusPage(req);
        int total = productStatusMapper.countProductStatus(req);

        return new PageResponseDTO<>(req, list, total);
    }

    @Transactional
    public int deleteOne(String pid) {
        productStatusMapper.deleteCartItems(pid);
        productStatusMapper.deleteCategoryLinks(pid);
        productStatusMapper.deleteOrderItems(pid);
        productStatusMapper.deleteProductDetail(pid);
        productStatusMapper.deleteProductOptions(pid);
        productStatusMapper.deleteProductReview(pid);
        productStatusMapper.deleteTagsLinks(pid);
        return productStatusMapper.deleteProduct(pid);
    }

    @Transactional
    public int deleteBulk(List<String> pids) {
        if (pids == null || pids.isEmpty()) return 0;
        productStatusMapper.deleteCartItemsBulk(pids);
        productStatusMapper.deleteCategoryLinksBulk(pids);
        productStatusMapper.deleteOrderItemsBulk(pids);
        productStatusMapper.deleteProductDetailBulk(pids);
        productStatusMapper.deleteProductOptionsBulk(pids);
        productStatusMapper.deleteProductReviewBulk(pids);
        productStatusMapper.deleteTagsLinksBulk(pids);
        return productStatusMapper.deleteProducts(pids);
    }
}