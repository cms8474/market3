package kr.co.team3.controller;

import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.service.my.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    private final ProductOrderService productOrderService;

    @GetMapping("/my/home")
    public String home(Model model){
        String loginId = "user01"; // 추후 로그인 세션이랑 연동
        List<ProductOrderDTO> orderDTOList = productOrderService.getRecent5(loginId);
        model.addAttribute("orderDTOList", orderDTOList);
        return "my/home";
    }

    @GetMapping("/my/modal/orderDetail")
    @ResponseBody
    public ProductOrderDTO orderDetail(@RequestParam("u_id") String u_id, @RequestParam("po_no") String po_no, @RequestParam("p_pid") String p_pid){
        return productOrderService.get1OrderItem(u_id, po_no, p_pid);
    }

    @GetMapping("/my/order")
    public String order(){
        return "my/order";
    }

    @GetMapping("/my/point")
    public String point(){
        return "my/point";
    }

    @GetMapping("/my/coupon")
    public String coupon(){
        return "my/coupon";
    }

    @GetMapping("/my/review")
    public String review(){
        return "my/review";
    }

    @GetMapping("/my/qna")
    public String qna(){
        return "my/qna";
    }

    @GetMapping("/my/info")
    public String info(){
        return "my/info";
    }

}
