package kr.co.team3.service.product;

import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.ProductDTO;
import kr.co.team3.mapper.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 강민철 2025-10-21 1703

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public List<ProductDTO> searchWithKeyword(PageRequestDTO pageRequestDTO) {
        return productMapper.selectWithKeyword(pageRequestDTO);
    }

    public int countWithKeyword(PageRequestDTO pageRequestDTO) {
        return productMapper.countWithKeyword(pageRequestDTO);
    }
}
