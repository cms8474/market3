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
public class AdminShopController {
    /*------------------shop--------------------*/
    @GetMapping(value = {"/shop/list"})
    public String shoplist(){
        System.out.println("go shoplist");
        return "admin/shop/list";
    }

    @GetMapping(value = {"/shop/sales"})
    public String shopsales(){
        System.out.println("go shopsales");
        return "admin/shop/sales";
    }
}
