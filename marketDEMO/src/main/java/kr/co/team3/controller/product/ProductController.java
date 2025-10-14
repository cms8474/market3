package kr.co.team3.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {


    @GetMapping("/product/list")
    public String productList(@RequestParam(required = false) String type) {
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
