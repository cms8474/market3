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
public class AdminMemberController {
    /*------------------member------------------*/
    @GetMapping(value = {"/member/list"})
    public String memberlist() {
        System.out.println("go member/list");
        return "admin/member/list";
    }


    @GetMapping(value = {"/member/point"})
    public String point() {
        System.out.println("go member/point");
        return "admin/member/point";
    }
}
