package kr.co.team3.product_service;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_repository.IndexRepository;
import kr.co.team3.product_repository.ProductReviewRepository;
import kr.co.team3.product_entity.SellerInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import jakarta.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndexService {

    private final IndexRepository indexRepository;
    private final ProductReviewRepository productReviewRepository;
    private final EntityManager entityManager;

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

    // 별점 평균 계산 메서드
    public Double getStarAverage(String pPid) {
        return productReviewRepository.findByPrPPidOrderByPrRegDateDesc(pPid, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent()
                .stream()
                .mapToDouble(review -> review.getPrStar())
                .average()
                .orElse(0.0);
    }

    // 판매자 정보 조회 메서드
    public String getSellerCompanyName(String pPid) {
        try {
            // PRODUCT_ITEM 테이블에서 상품의 판매자 ID 조회
            String query = "SELECT i.pSellerId FROM IndexEntity i WHERE i.pPid = :pPid";
            String sellerId = entityManager.createQuery(query, String.class)
                    .setParameter("pPid", pPid)
                    .getSingleResult();
            
            if (sellerId != null && !sellerId.trim().isEmpty()) {
                // SELLER_INFO 테이블에서 회사명 조회
                String sellerQuery = "SELECT s.sCompanyName FROM SellerInfoEntity s WHERE s.sUId = :sellerId";
                String companyName = entityManager.createQuery(sellerQuery, String.class)
                        .setParameter("sellerId", sellerId)
                        .getSingleResult();
                return companyName != null ? companyName : "판매자";
            }
        } catch (Exception e) {
            // 오류 발생시 기본값 반환
            System.out.println("판매자 정보 조회 오류 (pPid: " + pPid + "): " + e.getMessage());
        }
        return "판매자"; // 기본값
    }

    // 상품 목록에 별점 평균과 판매자 정보 추가
    public List<IndexDTO> getBestProductsWithStarAvg() {
        List<IndexDTO> products = getBestProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }

    public List<IndexDTO> getHitProductsWithStarAvg() {
        List<IndexDTO> products = getHitProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }

    public List<IndexDTO> getRecommendProductsWithStarAvg() {
        List<IndexDTO> products = getRecommendProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }

    public List<IndexDTO> getLatestProductsWithStarAvg() {
        List<IndexDTO> products = getLatestProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }

    public List<IndexDTO> getPopularProductsWithStarAvg() {
        List<IndexDTO> products = getPopularProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }

    public List<IndexDTO> getDiscountProductsWithStarAvg() {
        List<IndexDTO> products = getDiscountProducts();
        products.forEach(product -> {
            product.setStarAvg(getStarAverage(product.getPPid()));
            product.setSCompanyName(getSellerCompanyName(product.getPPid()));
        });
        return products;
    }
}