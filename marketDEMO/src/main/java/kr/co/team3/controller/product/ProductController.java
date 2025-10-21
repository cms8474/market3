package kr.co.team3.controller.product;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_entity.ProductOptionEntity;
import kr.co.team3.product_entity.ProductDetailEntity;
import kr.co.team3.product_entity.ProductReviewEntity;
import kr.co.team3.product_repository.ProductOptionRepository;
import kr.co.team3.product_repository.ProductDetailRepository;
import kr.co.team3.product_repository.ProductReviewRepository;
import kr.co.team3.product_service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.PageResponseDTO;
import kr.co.team3.dto.product.ProductDTO;
import kr.co.team3.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

// 강민철 2025-10-21 1703

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    private final IndexService indexService;
    private final ProductOptionRepository productOptionRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductReviewRepository productReviewRepository;

    @GetMapping("/product/list")
    public String productList(@RequestParam(required = false) String type, Model model) {

        List<IndexDTO> products = null;
        String categoryName = "";

        if (type == null || type.isEmpty()) {
            // type이 없으면 최신상품으로 기본 설정
            products = indexService.getLatestProducts();
            categoryName = "전체상품";
        } else {
            switch (type) {
                case "hit":
                    products = indexService.getHitProducts();
                    categoryName = "히트상품";
                    break;
                case "recommend":
                    products = indexService.getRecommendProducts();
                    categoryName = "추천상품";
                    break;
                case "latest":
                    products = indexService.getLatestProducts();
                    categoryName = "최신상품";
                    break;
                case "popular":
                    products = indexService.getPopularProducts();
                    categoryName = "인기상품";
                    break;
                case "discount":
                    products = indexService.getDiscountProducts();
                    categoryName = "할인상품";
                    break;
                default:
                    products = indexService.getLatestProducts();
                    categoryName = "전체상품";
                    break;
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryType", type);

        return "inc/product/list";
    }

    // 상품 상세 보기
    @GetMapping("/product/view")
    public String productView(@RequestParam(required = false) String pPid,
                             @RequestParam(defaultValue = "0") int page,
                             Model model) {
        if (pPid == null || pPid.isEmpty()) {
            return "redirect:/";
        }

        IndexDTO product = indexService.getProductById(pPid);
        if (product == null) {
            return "redirect:/";
        }

        // 상품 옵션 정보 조회
        List<ProductOptionEntity> options = productOptionRepository.findByPopPPid(pPid);

        // 옵션을 옵션명별로 그룹화
        Map<String, List<ProductOptionEntity>> groupedOptions = options.stream()
                .collect(Collectors.groupingBy(ProductOptionEntity::getPopName));

        // 상품정보 제공고시 조회
        Optional<ProductDetailEntity> productDetail = productDetailRepository.findByPdPid(pPid);

        // 리뷰 조회 (페이지네이션)
        Pageable pageable = PageRequest.of(page, 5); // 한 페이지당 5개 리뷰
        Page<ProductReviewEntity> reviewPage = productReviewRepository.findByPrPPidOrderByPrRegDateDesc(pPid, pageable);

        // 평균 별점 계산
        Double averageRating = productReviewRepository.findByPrPPidOrderByPrRegDateDesc(pPid, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent()
                .stream()
                .mapToDouble(ProductReviewEntity::getPrStar)
                .average()
                .orElse(0.0);

        // 별점 표시 로직 (정수 부분만 사용)
        int fullStars = (int) Math.floor(averageRating); // 정수 부분
        boolean hasHalfStar = (averageRating - fullStars) >= 0.5; // 0.5 이상이면 반별표시

        model.addAttribute("product", product);
        model.addAttribute("options", options);
        model.addAttribute("groupedOptions", groupedOptions);
        model.addAttribute("productDetail", productDetail.orElse(null));
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("reviewPage", reviewPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("fullStars", fullStars);
        model.addAttribute("hasHalfStar", hasHalfStar);

        return "inc/product/view";
    }


    @GetMapping("/product/order")
    public String productOrder() {
        return "inc/product/order";
    }

    @GetMapping("/product/complete")
    public String productComplete() {
        return "inc/product/complete";
    }

    @GetMapping("/product/search")
    public String productSearch(Model model, PageRequestDTO pageRequestDTO, String keyword) {
        // log.info("pageRequestDTO={}", pageRequestDTO);
        List<ProductDTO> searchedList = productService.searchWithKeyword(pageRequestDTO);
        int total = productService.countWithKeyword(pageRequestDTO);
        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .dtoList(searchedList)
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();
        model.addAttribute(pageResponseDTO);
        // log.info("pageResponseDTO={}", pageResponseDTO);
        return "inc/product/search";
    }
}