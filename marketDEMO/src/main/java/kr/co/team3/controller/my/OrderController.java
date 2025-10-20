package kr.co.team3.controller.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PageResponseDTO;
import kr.co.team3.service.my.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 강민철 2025-10-20 1457

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductOrderService productOrderService;
    private final PointService pointService;
    private final BoardService boardService;
    private final CouponService couponService;

    @GetMapping("/my/order")
    public String order(Model model, PageRequestDTO pageRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                loginId =  ((UserDetails) principal).getUsername(); // 아이디 반환
            }
        }

        // 나의쇼핑정보 (주문 수, 쿠폰 수, 포인트, 문의 수)
        int userPoints = pointService.getOwnPoints(loginId);
        model.addAttribute("userPoints", userPoints);
        int userOrderCount = productOrderService.getCountOrder(loginId);
        model.addAttribute("userOrderCount", userOrderCount);
        int userQnaCount = boardService.getNumberOfBoardsWithUidAndBtType(loginId, "qna");
        model.addAttribute("userQnaCount", userQnaCount);
        int userCouponCount = couponService.getNumberofCouponsWithUcUIdAndStatus(loginId, "사용가능");
        model.addAttribute("userCouponCount", userCouponCount);

        PageResponseDTO pageResponseDTO = productOrderService.selectAll(loginId, pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        return "my/order";
    }
}
