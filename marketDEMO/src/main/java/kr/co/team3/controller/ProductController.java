package kr.co.team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //product
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


    //member
    @GetMapping("/member/find/changePassword")
    public String changePassword() {
        return "inc/member/find/changePassword";
    }

    @GetMapping("/member/find/password1")
    public String password1() {
        return "inc/member/find/password1";
    }

    @GetMapping("/member/find/password2")
    public String password2() {
        return "inc/member/find/password2";
    }

    @GetMapping("/member/find/resultId")
    public String resultId() {
        return "inc/member/find/resultId";
    }

    @GetMapping("/member/find/userId1")
    public String userId1() {
        return "inc/member/find/userId1";
    }

    @GetMapping("/member/find/userId2")
    public String userId2() {
        return "inc/member/find/userId2";
    }

    @GetMapping("/member/join")
    public String join() {
        return "inc/member/join";
    }

    @GetMapping("/member/login")
    public String login() {
        return "inc/member/login";
    }

    @GetMapping("/member/register")
    public String register() {
        return "inc/member/register";
    }

    @GetMapping("/member/registerSeller")
    public String registerSeller() {
        return "inc/member/registerSeller";
    }

    @GetMapping("/member/signup")
    public String signup() {
        return "inc/member/signup";
    }


    //cs
    @GetMapping("/cs/index")
    public String cs() {
        return "inc/cs/index";
    }

    @GetMapping("/cs/notice/list")
    public String noticeList() {
        return "inc/cs/notice/list";
    }

    @GetMapping("/cs/notice/view")
    public String noticeView() {
        return "inc/cs/notice/view";
    }

    @GetMapping("/cs/faq/list")
    public String faqList() {
        return "inc/cs/faq/list";
    }

    @GetMapping("/cs/faq/view")
    public String faqView() {
        return "inc/cs/faq/view";
    }

    @GetMapping("/cs/qna/list")
    public String qnaList() {
        return "inc/cs/qna/list";
    }

    @GetMapping("/cs/qna/view")
    public String qnaView() {
        return "inc/cs/qna/view";
    }

    @GetMapping("/cs/qna/write")
    public String qnaWrite() {
        return "inc/cs/qna/write";
    }
}