package kr.co.team3.controller.admin;

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
    @GetMapping(value = {"/coupon/list"})
    public String couponlist() {
        System.out.println("go coupon/list");
        return "admin/coupon/list";
    }

    @GetMapping(value = {"/coupon/issued"})
    public String couponissued() {
        System.out.println("go coupon/issued");
        return "admin/coupon/issued";
    }

}
