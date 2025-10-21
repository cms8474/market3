package kr.co.team3.controller.product;

import kr.co.team3.product_entity.CartItemsEntity;
import kr.co.team3.product_entity.ProductOptionEntity;
import kr.co.team3.product_service.CartService;
import kr.co.team3.product_service.IndexService;
import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final IndexService indexService;
    private final ProductOptionRepository productOptionRepository;

    /**
     * 장바구니에 상품 추가
     * @param pPid 상품 ID
     * @param ciOp01 선택한 옵션1의 POP_NO
     * @param ciOp02 선택한 옵션2의 POP_NO
     * @param ciAmount 구매 수량
     * @param ciTotPrice 총 가격
     * @param authentication 현재 로그인한 사용자 정보
     * @return 장바구니 페이지로 리다이렉트
     */
    @PostMapping("/product/cart/add")
    public String addToCart(@RequestParam String pPid,
                           @RequestParam(required = false) String ciOp01,
                           @RequestParam(required = false) String ciOp02,
                           @RequestParam Integer ciAmount,
                           @RequestParam Integer ciTotPrice,
                           Authentication authentication) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";
        }
        
        String userId = authentication.getName();
        
        cartService.addToCart(userId, pPid, ciOp01, ciOp02, ciAmount, ciTotPrice);
        
        return "redirect:/product/cart";
    }

    /**
     * 장바구니 페이지 조회
     * @param authentication 현재 로그인한 사용자 정보
     * @param model 뷰에 전달할 데이터
     * @return 장바구니 페이지
     */
    @GetMapping("/product/cart")
    public String cartPage(Authentication authentication, Model model) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";
        }
        
        String userId = authentication.getName();
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
        
        // 합계/개수 계산 (널 안전)
        int totalAmount = cartItems.stream()
                .mapToInt(ci -> ci.getCiTotPrice() != null ? ci.getCiTotPrice() : 0)
                .sum();
        int totalCount = cartItems.size();
        
        // 배송비 합계 계산
        int totalDeliveryFee = cartItems.stream()
                .mapToInt(ci -> {
                    IndexDTO product = productMap.get(ci.getCiPPid());
                    return product != null && product.getPDeliveryPrice() != null ? product.getPDeliveryPrice() : 0;
                })
                .sum();
        
        // 전체주문금액 = 상품금액 + 배송비
        int finalOrderAmount = totalAmount + totalDeliveryFee;

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("productMap", productMap);
        model.addAttribute("optionMap", optionMap);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalDeliveryFee", totalDeliveryFee);
        model.addAttribute("finalOrderAmount", finalOrderAmount);
        
        return "inc/product/cart";
    }

    /**
     * 장바구니에서 상품 삭제
     * @param pPid 상품 ID
     * @param authentication 현재 로그인한 사용자 정보
     * @return 장바구니 페이지로 리다이렉트
     */
    @PostMapping("/product/cart/remove")
    public String removeFromCart(@RequestParam String pPid, Authentication authentication) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/member/login";
        }
        
        String userId = authentication.getName();
        cartService.removeFromCart(userId, pPid);
        
        return "redirect:/product/cart";
    }
}
