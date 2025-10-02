package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/my/home")
    public String home(){
        return "my/home";
    }

    @GetMapping("/my/order")
    public String order(){
        return "my/order";
    }

}
