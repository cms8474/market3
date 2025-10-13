package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    // 메인 페이지
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // =======================
    // ✅ 상품 관련 페이지
    // =======================
    @GetMapping("/product/list")
    public String productList() {
        return "inc/product/list";
    }

    @GetMapping("/product/view")
    public String productView() {
        return "inc/product/view";
    }

    @GetMapping("/product/cart")
    public String productCart() {
        return "inc/product/cart";
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
    public String productSearch() {
        return "inc/product/search";
    }

}
