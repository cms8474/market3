package kr.co.team3.controller.product;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_service.MemberService;
import kr.co.team3.security.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* ================================
       ✅ 회원가입 관련
    ================================= */

    /** 회원가입 폼 (signup, join 둘 다 허용) */
    @GetMapping({"/signup", "/join"})
    public String signupForm() {
        return "inc/member/join"; // ✅ templates/inc/member/signup.html
    }

    /** 회원가입 처리 */
    @PostMapping("/join")
    public String register(@ModelAttribute MemberEntity member) {
        memberService.register(member);
        return "redirect:/member/join";
    }

    /** 일반 회원가입 페이지 */
    @GetMapping("/register")
    public String registerPage() {
        return "inc/member/register"; // ✅ templates/inc/member/register.html
    }

    /** 판매자 회원가입 페이지 */
    @GetMapping("/registerSeller")
    public String registerSellerPage() {
        return "inc/member/registerSeller"; // ✅ templates/inc/member/registerSeller.html
    }



    /* ================================
       ✅ 로그인/로그아웃 관련
    ================================= */

    /** 로그인 페이지 */
    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        // 이미 로그인된 사용자는 메인 페이지로 리다이렉트
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            return "redirect:/";
        }
        return "inc/member/login"; // ✅ templates/inc/member/login.html
    }

    // ✅ 로그아웃 (백업용 - Spring Security가 처리하지 못할 경우)
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        
        // remember-me 쿠키 삭제
        Cookie rememberMeCookie = new Cookie("remember-me", null);
        rememberMeCookie.setMaxAge(0);
        rememberMeCookie.setPath("/");
        response.addCookie(rememberMeCookie);
        
        // JSESSIONID 쿠키 삭제
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        
        return "redirect:/";
    }



    /* ================================
       ✅ 아이디 / 비밀번호 찾기
    ================================= */

    /** 아이디 찾기 페이지 */
    @GetMapping("/findId")
    public String findIdForm() {
        return "inc/member/findId"; // ✅ templates/inc/member/findId.html
    }

    /** 비밀번호 찾기 페이지 */
    @GetMapping("/findPw")
    public String findPwForm() {
        return "inc/member/findPw"; // ✅ templates/inc/member/findPw.html
    }

    /** 비밀번호 변경 */
    @PostMapping("/changePw")
    public String changePw(@RequestParam String uId, @RequestParam String newPw) {
        memberService.changePassword(uId, newPw);
        return "redirect:/member/login";
    }

    /** 하위 find 페이지 (password1, password2, resultId 등) */
    @GetMapping("/find/{page}")
    public String findPages(@PathVariable String page) {
        return "inc/member/find/" + page; // ✅ templates/inc/member/find/{page}.html
    }


    /* ================================
       ✅ 회원 단일 조회 (Ajax 등)
    ================================= */
    @GetMapping("/{uId}")
    @ResponseBody
    public MemberEntity getMember(@PathVariable String uId) {
        return memberService.getMember(uId).orElse(null);
    }


    /* ================================
       ✅ 비밀번호 암호화
    ================================= */
    @GetMapping("/update-passwords")
    public String updatePasswords() {
        memberService.updatePasswordToEncrypted();
        return "redirect:/";
    }



    
}
