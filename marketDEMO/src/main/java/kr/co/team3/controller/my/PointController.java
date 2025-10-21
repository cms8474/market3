package kr.co.team3.controller.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PageResponseDTO;
import kr.co.team3.service.my.BoardService;
import kr.co.team3.service.my.CouponService;
import kr.co.team3.service.my.PointService;
import kr.co.team3.service.my.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 강민철 2025-10-21 1214

@Controller
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/my/point")
    public String point(Model model, PageRequestDTO pageRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                loginId =  ((UserDetails) principal).getUsername(); // 아이디 반환
            }
        }

        PageResponseDTO pageResponseDTO = pointService.selectAll(loginId, pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "my/point";
    }
}
