package kr.co.team3.controller.my;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

// 강민철 2025-10-20 1457

@Controller
@RequiredArgsConstructor
public class PointController {

    @GetMapping("/my/point")
    public String point(){
        return "my/point";
    }
}
