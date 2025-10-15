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
public class AdminOrderController {
    /*------------------order------------------*/
    @GetMapping(value = {"/order/list"})
    public String orderlist(){
        System.out.println("go order/list");
        return "admin/order/list";
    }

    @GetMapping(value = {"/order/delivery"})
    public String orderdelivery() {
        System.out.println("go order/delivery");
        return "/admin/order/delivery";
    }

}
