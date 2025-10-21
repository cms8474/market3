package kr.co.team3.product_service;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_repository.IndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndexService {

    private final IndexRepository indexRepository;

    public List<IndexDTO> getMainBanners() {
        return indexRepository.findByAdLocation("TOP");
    }

    public List<IndexDTO> getSlideBanners() {
        return indexRepository.findByAdLocation("SLIDE");
    }

    public List<IndexDTO> getCategories() {
        return indexRepository.findCategories();
    }

    public List<IndexDTO> getBestProducts() {
        Pageable limit = PageRequest.of(0, 5);
        return indexRepository.findBestProducts(limit);
    }

    public List<IndexDTO> getHitProducts() {
        Pageable limit = PageRequest.of(0, 8);
        return indexRepository.findHitProducts(limit);
    }

    public List<IndexDTO> getRecommendProducts() {
        Pageable limit = PageRequest.of(0, 8);
        return indexRepository.findRecommendProducts(limit);
    }

    public List<IndexDTO> getLatestProducts() {
        Pageable limit = PageRequest.of(0, 8);
        return indexRepository.findLatestProducts(limit);
    }

    // 인기상품 추가 (조회수 높은 순)
    public List<IndexDTO> getPopularProducts() {
        Pageable limit = PageRequest.of(0, 8);
        return indexRepository.findPopularProducts(limit);
    }

    public List<IndexDTO> getDiscountProducts() {
        Pageable limit = PageRequest.of(0, 8);
        return indexRepository.findDiscountProducts(limit);
    }

    // 상품 상세 조회
    public IndexDTO getProductById(String pPid) {
        return indexRepository.findProductById(pPid);
    }

    public List<IndexDTO> getCompanyInfo() {
        return indexRepository.findCompanyInfo();
    }
}