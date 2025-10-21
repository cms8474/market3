package kr.co.team3.controller.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PageResponseDTO;
import kr.co.team3.service.my.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

// 강민철 2025-10-21 1214

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final ProductOrderService productOrderService;

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

        PageResponseDTO pageResponseDTO = productOrderService.selectAll(loginId, pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        log.info("Order Page : {}", pageResponseDTO);

        return "my/order";
    }

    @GetMapping("/my/modal/confirm/{orderNum}")
    @ResponseBody
    public ResponseEntity<Boolean> confirmOrder(@PathVariable("orderNum") String orderNum) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                loginId =  ((UserDetails) principal).getUsername(); // 아이디 반환
            }
        }
        log.info("Order Confirmation {}, {}", orderNum, loginId);
        boolean modifyCheck = productOrderService.modifyPoState(loginId, orderNum);
        return ResponseEntity.ok(modifyCheck);
    }
}
