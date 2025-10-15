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
public class AdminProductController {
    /*------------------product------------------*/
    @GetMapping(value = {"/product/list"})
    public String productlist(){
        System.out.println("go productlist");
        return "admin/product/list";
    }

    @GetMapping(value = {"/product/register"})
    public String productregister(){
        System.out.println("go productlist");
        return "admin/product/register";
    }
}
