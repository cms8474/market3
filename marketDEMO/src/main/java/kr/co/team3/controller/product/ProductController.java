package kr.co.team3.controller.product;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_entity.ProductOptionEntity;
import kr.co.team3.product_entity.ProductDetailEntity;
import kr.co.team3.product_entity.ProductReviewEntity;
import kr.co.team3.product_entity.CartItemsEntity;
import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_repository.ProductOptionRepository;
import kr.co.team3.product_repository.ProductDetailRepository;
import kr.co.team3.product_repository.ProductReviewRepository;
import kr.co.team3.product_service.IndexService;
import kr.co.team3.product_service.CartService;
import kr.co.team3.product_service.MemberService;
import kr.co.team3.service.my.CouponService;
import kr.co.team3.admin_service.AdminCouponService;
import kr.co.team3.entity.my.Coupon;
import kr.co.team3.admin_dto.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final IndexService indexService;
    private final ProductOptionRepository productOptionRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductReviewRepository productReviewRepository;
    private final CartService cartService;
    private final MemberService memberService;
    private final CouponService couponService;
    private final AdminCouponService adminCouponService;

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
    public String productOrder(Authentication authentication, Model model) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";
        }
        
        String userId = authentication.getName();
        
        // 장바구니 아이템 조회
        List<CartItemsEntity> cartItems = cartService.getCartItems(userId);
        
        // 각 장바구니 아이템에 대한 상품 정보와 옵션 정보 조회
        Map<String, IndexDTO> productMap = new HashMap<>();
        Map<String, ProductOptionEntity> optionMap = new HashMap<>();
        
        for (CartItemsEntity cartItem : cartItems) {
            // 상품 정보 조회
            IndexDTO product = indexService.getProductById(cartItem.getCiPPid());
            if (product != null) {
                productMap.put(cartItem.getCiPPid(), product);
            }
            
            // 옵션 정보 조회
            if (cartItem.getCiOp01() != null && !cartItem.getCiOp01().isEmpty()) {
                ProductOptionEntity option1 = productOptionRepository.findById(cartItem.getCiOp01()).orElse(null);
                if (option1 != null) {
                    optionMap.put(cartItem.getCiOp01(), option1);
                }
            }
            
            if (cartItem.getCiOp02() != null && !cartItem.getCiOp02().isEmpty()) {
                ProductOptionEntity option2 = productOptionRepository.findById(cartItem.getCiOp02()).orElse(null);
                if (option2 != null) {
                    optionMap.put(cartItem.getCiOp02(), option2);
                }
            }
        }
        
        // 사용자 정보 조회
        Optional<MemberEntity> memberOpt = memberService.getMember(userId);
        MemberEntity member = memberOpt.orElse(null);
        
        // 합계/개수 계산
        int totalAmount = cartItems.stream()
                .mapToInt(ci -> ci.getCiTotPrice() != null ? ci.getCiTotPrice() : 0)
                .sum();
        int totalCount = cartItems.size();
        
        // 적립 포인트 계산
        int totalPoints = cartItems.stream()
                .mapToInt(ci -> {
                    IndexDTO product = productMap.get(ci.getCiPPid());
                    return product != null && product.getPPoint() != null ? product.getPPoint() : 0;
                })
                .sum();
        
        // 최종결제정보 계산
        // 상품수: CART_ITEMS의 행 수
        int productCount = cartItems.size();
        
        // 상품금액: 모든 상품의 P_PRICE의 총합 (할인 전 원래 가격)
        int productAmount = cartItems.stream()
                .mapToInt(ci -> {
                    IndexDTO product = productMap.get(ci.getCiPPid());
                    if (product != null && product.getPPrice() != null && ci.getCiAmount() != null) {
                        return product.getPPrice() * ci.getCiAmount();
                    }
                    return 0;
                })
                .sum();
        
        // 할인금액: 원래 가격에서 실제 결제 금액을 뺀 차이
        int discountAmount = cartItems.stream()
                .mapToInt(ci -> {
                    IndexDTO product = productMap.get(ci.getCiPPid());
                    if (product != null && product.getPPrice() != null && ci.getCiAmount() != null && ci.getCiTotPrice() != null) {
                        int originalPrice = product.getPPrice() * ci.getCiAmount();
                        int discountedPrice = ci.getCiTotPrice();
                        return originalPrice - discountedPrice; // 할인된 금액
                    }
                    return 0;
                })
                .sum();
        
        // 배송비: PRODUCT_ITEM의 p_delivery_price 합계
        int deliveryFee = cartItems.stream()
                .mapToInt(ci -> {
                    IndexDTO product = productMap.get(ci.getCiPPid());
                    return product != null && product.getPDeliveryPrice() != null ? product.getPDeliveryPrice() : 0;
                })
                .sum();
        
        // 추가할인: 기본값 0 (쿠폰 사용 시 계산)
        int additionalDiscount = 0;
        
        // 전체주문금액: 상품금액 - 할인금액 - 배송비 - 추가할인
        int finalAmount = productAmount - discountAmount - deliveryFee - additionalDiscount;
        
        // 사용자의 사용 가능한 쿠폰 목록 조회
        List<Coupon> userCoupons = couponService.getAvailableCouponsByUserId(userId);
        
        // 쿠폰 상세 정보 조회 (COUPON 테이블의 C_NAME 정보)
        Map<String, CouponDTO> couponDetailMap = new HashMap<>();
        for (Coupon userCoupon : userCoupons) {
            CouponDTO couponDetail = adminCouponService.getCouponDetail(userCoupon.getUcCId());
            if (couponDetail != null) {
                couponDetailMap.put(userCoupon.getUcCId(), couponDetail);
            }
        }
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("productMap", productMap);
        model.addAttribute("optionMap", optionMap);
        model.addAttribute("member", member);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPoints", totalPoints);
        model.addAttribute("userCoupons", userCoupons);
        model.addAttribute("couponDetailMap", couponDetailMap);
        
        // 최종결제정보 모델 추가
        model.addAttribute("productCount", productCount);
        model.addAttribute("productAmount", productAmount);
        model.addAttribute("discountAmount", discountAmount);
        model.addAttribute("deliveryFee", deliveryFee);
        model.addAttribute("additionalDiscount", additionalDiscount);
        model.addAttribute("finalAmount", finalAmount);
        
        return "inc/product/order";
    }

    @GetMapping("/product/complete")
    public String productComplete() {
        return "inc/product/complete";
    }

    @GetMapping("/product/search")
    public String productSearch() {
        return "inc/product/search";
    }
}