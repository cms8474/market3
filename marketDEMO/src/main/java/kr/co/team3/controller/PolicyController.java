package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import kr.co.team3.policy_service.TermsService;
import lombok.RequiredArgsConstructor;
import kr.co.team3.policy_dto.TermsDTO;

@RequiredArgsConstructor
@Controller
public class PolicyController {

    private final TermsService termsService;

    @GetMapping("/policy/buyer")
    public String buyer(Model model){
        model.addAttribute("terms", termsService.getTerms("구매회원 약관"));
        return "policy/buyer";
    }

    @GetMapping("/policy/seller")
    public String seller(Model model){
        model.addAttribute("terms", termsService.getTerms("판매회원 약관"));
        return "policy/seller";
    }

    @GetMapping("/policy/finance")
    public String finance(Model model){
        model.addAttribute("terms", termsService.getTerms("전자금융거래 약관"));
        return "policy/finance";
    }

    @GetMapping("/policy/location")
    public String location(Model model){
        model.addAttribute("terms", termsService.getTerms("위치정보 이용약관"));
        return "policy/location";
    }

    @GetMapping("/policy/privacy")
    public String privacy(Model model){
        model.addAttribute("terms", termsService.getTerms("개인정보 처리방침"));
        return "policy/privacy";
    }
}
