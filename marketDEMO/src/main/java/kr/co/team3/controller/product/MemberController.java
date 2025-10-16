package kr.co.team3.controller.product;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_entity.SellerInfoEntity;
import kr.co.team3.product_service.MemberService;
import kr.co.team3.security.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* ================================
       ✅ 회원가입 관련
    ================================= */
    /** ✅ DB 연결 테스트 */
    @GetMapping("/test-db")
    @ResponseBody
    public Map<String, Object> testDatabase() {
        Map<String, Object> result = new HashMap<>();
        try {
            // 기존 existsById 메서드를 활용해서 DB 연결 테스트
            boolean exists = memberService.existsById("TEST_DB_CONNECTION");
            result.put("success", true);
            result.put("message", "DB 연결 성공");
            result.put("testQuery", "existsById 테스트 완료");
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "DB 연결 실패: " + e.getMessage());
            return result;
        }
    }

    /** 회원가입 폼 (signup, join 둘 다 허용) */
    @GetMapping({"/signup", "/join"})
    public String signupForm() {
        return "inc/member/join"; // ✅ templates/inc/member/signup.html
    }

    /** 일반 회원가입 페이지 */
    @GetMapping("/register")
    public String registerPage() {
        return "inc/member/register"; // ✅ templates/inc/member/register.html
    }

    /** 아이디 중복확인 */
    @GetMapping("/checkId/{uId}")
    @ResponseBody
    public Map<String, Boolean> checkId(@PathVariable String uId) {
        boolean exists = memberService.existsById(uId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return result;
    }

    /** 이메일 중복확인 */
    @GetMapping("/checkEmail/{uMail}")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@PathVariable String uMail) {
        boolean exists = memberService.existsByMail(uMail);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return result;
    }

    /** 회원가입 처리 */
    @PostMapping("/join")
    public String register(@ModelAttribute MemberEntity member, Model model) {
        try {
            System.out.println("=== 회원가입 요청 디버그 ===");
            System.out.println("U_ID: " + member.getUId());
            System.out.println("U_PW: " + member.getUPw());
            System.out.println("U_NAME: " + member.getUName());
            System.out.println("U_BIRTH: " + member.getUBirth());
            System.out.println("U_GENDER: " + member.getUGender());
            System.out.println("U_MAIL: " + member.getUMail());
            System.out.println("U_PHONE: " + member.getUPhone());
            System.out.println("U_POSTAL: " + member.getUPostal());
            System.out.println("U_BASE_ADDR: " + member.getUBaseAddr());
            System.out.println("U_DETAIL_ADDR: " + member.getUDetailAddr());
            
            // 필수 필드 검증
            if (member.getUId() == null || member.getUId().trim().isEmpty()) {
                System.out.println("아이디 검증 실패");
                model.addAttribute("error", "아이디를 입력해주세요.");
                return "inc/member/register";
            }
            if (member.getUPw() == null || member.getUPw().trim().isEmpty()) {
                System.out.println("비밀번호 검증 실패");
                model.addAttribute("error", "비밀번호를 입력해주세요.");
                return "inc/member/register";
            }
            if (member.getUName() == null || member.getUName().trim().isEmpty()) {
                System.out.println("이름 검증 실패");
                model.addAttribute("error", "이름을 입력해주세요.");
                return "inc/member/register";
            }
            if (member.getUMail() == null || member.getUMail().trim().isEmpty()) {
                System.out.println("이메일 검증 실패");
                model.addAttribute("error", "이메일을 입력해주세요.");
                return "inc/member/register";
            }
            if (member.getUPhone() == null || member.getUPhone().trim().isEmpty()) {
                System.out.println("휴대폰 검증 실패");
                model.addAttribute("error", "휴대폰 번호를 입력해주세요.");
                return "inc/member/register";
            }
            
            System.out.println("필수 필드 검증 통과");
            
            // 기본값 설정 - 실제 DB 요구사항에 맞게
            member.setUType("일반");
            member.setUPoint(10000);  // NUMBER 타입이므로 10000
            member.setURank("family"); // "family" 값
            member.setUStatus("정상"); // "정상" 값
            // uCreateDay는 트리거로 자동 생성되므로 설정하지 않음
            // uLastLoginAt은 NULL 허용이므로 설정하지 않음
            
            System.out.println("기본값 설정 완료");
            
            // 회원가입 처리
            System.out.println("회원가입 서비스 호출 시작");
            
            // 회원가입 처리 (타임아웃 없이)
            System.out.println("=== MemberService.register() 호출 직전 ===");
            MemberEntity savedMember = memberService.register(member);
            System.out.println("=== MemberService.register() 호출 완료 ===");
            
            if (savedMember != null) {
                System.out.println("회원가입 성공: " + savedMember.getUId());
                return "redirect:/member/login?success=true";
            } else {
                System.out.println("회원가입 실패: savedMember가 null");
                model.addAttribute("error", "회원가입에 실패했습니다.");
                return "inc/member/register";
            }
        } catch (Exception e) {
            System.out.println("=== 회원가입 Controller 오류 ===");
            System.out.println("오류 메시지: " + e.getMessage());
            System.out.println("오류 클래스: " + e.getClass().getName());
            System.out.println("오류 원인: " + (e.getCause() != null ? e.getCause().getMessage() : "없음"));
            e.printStackTrace();
            
            // 웹페이지에 상세 오류 표시
            String errorMessage = "회원가입 중 오류가 발생했습니다: " + e.getMessage();
            if (e.getCause() != null) {
                errorMessage += " (원인: " + e.getCause().getMessage() + ")";
            }
            model.addAttribute("error", errorMessage);
            return "inc/member/register";
        }
    }

    

    /** 판매자 회원가입 페이지 */
    @GetMapping("/registerSeller")
    public String registerSellerPage() {
        return "inc/member/registerSeller"; // ✅ templates/inc/member/registerSeller.html
    }

    /** 판매자 회원가입 처리 */
    @PostMapping("/joinSeller")
    public String registerSeller(
            @ModelAttribute MemberEntity member,
            @RequestParam String sCompanyName,
            @RequestParam String sSellerNo,
            @RequestParam String sSalesRegNum,
            @RequestParam String sFax,
            Model model) {
        try {
            System.out.println("=== 판매자 회원가입 요청 디버그 ===");
            System.out.println("U_USER 정보:");
            System.out.println("  U_ID: " + member.getUId());
            System.out.println("  U_PW: " + member.getUPw());
            System.out.println("  U_NAME: " + member.getUName());
            System.out.println("  U_BIRTH: " + member.getUBirth());
            System.out.println("  U_GENDER: " + member.getUGender());
            System.out.println("  U_MAIL: " + member.getUMail());
            System.out.println("  U_PHONE: " + member.getUPhone());
            System.out.println("  U_POSTAL: " + member.getUPostal());
            System.out.println("  U_BASE_ADDR: " + member.getUBaseAddr());
            System.out.println("  U_DETAIL_ADDR: " + member.getUDetailAddr());
            
            System.out.println("SELLER_INFO 정보:");
            System.out.println("  S_COMPANY_NAME: " + sCompanyName);
            System.out.println("  S_SELLER_NO: " + sSellerNo);
            System.out.println("  S_SALES_REG_NUM: " + sSalesRegNum);
            System.out.println("  S_FAX: " + sFax);
            
            // 필수 필드 검증
            if (member.getUId() == null || member.getUId().trim().isEmpty()) {
                System.out.println("아이디 검증 실패");
                model.addAttribute("error", "아이디를 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (member.getUPw() == null || member.getUPw().trim().isEmpty()) {
                System.out.println("비밀번호 검증 실패");
                model.addAttribute("error", "비밀번호를 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (member.getUName() == null || member.getUName().trim().isEmpty()) {
                System.out.println("이름 검증 실패");
                model.addAttribute("error", "이름을 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (member.getUMail() == null || member.getUMail().trim().isEmpty()) {
                System.out.println("이메일 검증 실패");
                model.addAttribute("error", "이메일을 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (member.getUPhone() == null || member.getUPhone().trim().isEmpty()) {
                System.out.println("휴대폰 검증 실패");
                model.addAttribute("error", "휴대폰 번호를 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (sCompanyName == null || sCompanyName.trim().isEmpty()) {
                System.out.println("회사명 검증 실패");
                model.addAttribute("error", "회사명을 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (sSellerNo == null || sSellerNo.trim().isEmpty()) {
                System.out.println("사업자등록번호 검증 실패");
                model.addAttribute("error", "사업자등록번호를 입력해주세요.");
                return "inc/member/registerSeller";
            }
            if (sSalesRegNum == null || sSalesRegNum.trim().isEmpty()) {
                System.out.println("통신판매업번호 검증 실패");
                model.addAttribute("error", "통신판매업번호를 입력해주세요.");
                return "inc/member/registerSeller";
            }
            System.out.println("필수 필드 검증 통과");
            
            // U_USER 기본값 설정
            member.setUType("판매자");
            member.setUPoint(10000);
            member.setURank("family");
            member.setUStatus("정상");
            
            // SELLER_INFO 객체 생성
            SellerInfoEntity sellerInfo = SellerInfoEntity.builder()
                    .sUId(member.getUId())
                    .sCompanyName(sCompanyName)
                    .sSellerNo(sSellerNo)
                    .sSalesRegNum(sSalesRegNum)
                    .sTel(member.getUPhone()) // 휴대폰 번호를 전화번호로 사용
                    .sFax(sFax)
                    .sState("운영준비")
                    .sSellerType("확인 후 입력")
                    .build();
            
            System.out.println("판매자 기본값 설정 완료");
            
            // 판매자 회원가입 처리
            System.out.println("판매자 회원가입 서비스 호출 시작");
            MemberEntity savedMember = memberService.registerSeller(member, sellerInfo);
            System.out.println("판매자 회원가입 서비스 호출 완료");
            
            if (savedMember != null) {
                System.out.println("판매자 회원가입 성공: " + savedMember.getUId());
                return "redirect:/member/login?success=true";
            } else {
                System.out.println("판매자 회원가입 실패: savedMember가 null");
                model.addAttribute("error", "판매자 회원가입에 실패했습니다.");
                return "inc/member/registerSeller";
            }
        } catch (Exception e) {
            System.out.println("=== 판매자 회원가입 Controller 오류 ===");
            System.out.println("오류 메시지: " + e.getMessage());
            System.out.println("오류 클래스: " + e.getClass().getName());
            System.out.println("오류 원인: " + (e.getCause() != null ? e.getCause().getMessage() : "없음"));
            e.printStackTrace();
            
            // 웹페이지에 상세 오류 표시
            String errorMessage = "판매자 회원가입 중 오류가 발생했습니다: " + e.getMessage();
            if (e.getCause() != null) {
                errorMessage += " (원인: " + e.getCause().getMessage() + ")";
            }
            model.addAttribute("error", errorMessage);
            return "inc/member/registerSeller";
        }
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

    /** 이메일 인증번호 발송 */
    @PostMapping("/sendEmailCode")
    @ResponseBody
    public Map<String, Object> sendEmailCode(@RequestBody Map<String, String> request) {
        System.out.println("=== MemberController.sendEmailCode() 호출됨 ===");
        System.out.println("요청 데이터: " + request);
        
        String email = request.get("email");
        Map<String, Object> result = new HashMap<>();
        
        try {
            System.out.println("=== 이메일 인증번호 발송 요청 ===");
            System.out.println("요청 이메일: " + email);
            
            if (email == null || email.trim().isEmpty()) {
                System.out.println("이메일이 null이거나 비어있음");
                result.put("success", false);
                result.put("message", "이메일을 입력해주세요.");
                return result;
            }
            
            System.out.println("MemberService.sendEmailVerificationCode() 호출 시작");
            Map<String, Object> serviceResult = memberService.sendEmailVerificationCode(email);
            System.out.println("MemberService.sendEmailVerificationCode() 호출 완료");
            
            // MemberService에서 반환된 결과를 그대로 전달
            result.put("success", serviceResult.get("success"));
            result.put("message", serviceResult.get("message"));
            
            System.out.println("이메일 발송 결과: " + serviceResult.get("success") + " - " + serviceResult.get("message"));
            System.out.println("=== MemberController.sendEmailCode() 완료 ===");
            
            return result;
        } catch (Exception e) {
            System.out.println("이메일 발송 컨트롤러 오류: " + e.getMessage());
            System.out.println("예외 클래스: " + e.getClass().getName());
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "이메일 발송 중 오류가 발생했습니다: " + e.getMessage());
            return result;
        }
    }

    /** 이메일 인증번호 확인 */
    @PostMapping("/verifyEmailCode")
    @ResponseBody
    public Map<String, Object> verifyEmailCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        Map<String, Object> result = new HashMap<>();
        
        try {
            boolean verified = memberService.verifyEmailCode(email, code);
            if (verified) {
                result.put("success", true);
                result.put("message", "이메일 인증이 완료되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "인증번호가 일치하지 않습니다.");
            }
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "인증 확인 중 오류가 발생했습니다: " + e.getMessage());
            return result;
        }
    }

    /** 약관 내용 조회 */
    @GetMapping("/getTerms/{termName}")
    @ResponseBody
    public String getTerms(@PathVariable String termName) {
        return memberService.getTermsContent(termName);
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
        // TODO: MyBatis로 비밀번호 암호화 기능 구현 필요
        System.out.println("비밀번호 암호화 기능은 MyBatis로 구현 필요");
        return "redirect:/";
    }



    
}
