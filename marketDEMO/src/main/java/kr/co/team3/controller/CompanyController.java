package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {
    @GetMapping("/company/index")
    public String index(){
        return "company/index";
    }

    @GetMapping("/company/culture")
    public String culture(){
        return "company/culture";
    }

    @GetMapping("/company/story")
    public String story(){
        return "company/story";
    }

    @GetMapping("/company/recruit")
    public String recruit(){
        return "company/recruit";
    }

    @GetMapping("/company/media")
    public String media(){
        return "company/media";
    }
}
