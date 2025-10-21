package kr.co.team3.service.product;

import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.ProductDTO;
import kr.co.team3.mapper.product.ProductMapper;
import kr.co.team3.product_repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

// 강민철 2025-10-21 1703

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductReviewRepository productReviewRepository;

    public List<ProductDTO> searchWithKeyword(PageRequestDTO pageRequestDTO) {
        List<ProductDTO> products = productMapper.selectWithKeyword(pageRequestDTO);
        // 각 상품에 별점 평균 추가
        products.forEach(product -> {
            Double starAvg = productReviewRepository.findByPrPPidOrderByPrRegDateDesc(product.getPPId(), PageRequest.of(0, Integer.MAX_VALUE))
                    .getContent()
                    .stream()
                    .mapToDouble(review -> review.getPrStar())
                    .average()
                    .orElse(0.0);
            product.setStarAvg(starAvg);
        });
        return products;
    }

    public int countWithKeyword(PageRequestDTO pageRequestDTO) {
        return productMapper.countWithKeyword(pageRequestDTO);
    }
}
