package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.AdminCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCouponController {

    /*------------------coupon------------------*/
    private final AdminCouponService adminCouponService;

    @GetMapping("/coupon/list")
    public String list(PageRequestDTO req, Model model) {
        // 방어코드 (음수 등)
        if (req.getPg() <= 0) req.setPg(1);
        if (req.getSize() <= 0) req.setSize(10);

        PageResponseDTO<CouponDTO> page = adminCouponService.getCouponPage(req);

        model.addAttribute("page", page);
        model.addAttribute("req", req);
        return "admin/coupon/list";
    }

    @GetMapping(value = {"/coupon/issued"})
    public String couponissued() {
        System.out.println("go coupon/issued");
        return "admin/coupon/issued";
    }

}
