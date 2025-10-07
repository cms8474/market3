package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/","/index"})
    public String index(){
        System.out.println("go index");
        return "index";
    }

    @GetMapping(value = {"/exPage"})
    public String exPage(){
        System.out.println("go exPage");
        return "exPage";
    }


    // 유진님 파트 진입 테스트 코드
    @GetMapping("/login")
    public String login() {
        return "inc/admin/shop/sales";
    }

}
