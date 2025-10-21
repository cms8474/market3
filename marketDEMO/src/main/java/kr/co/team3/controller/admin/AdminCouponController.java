package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.CouponIssueDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.AdminCouponService;
import kr.co.team3.admin_service.CouponIssueService;
import kr.co.team3.admin_mapper.UserMapper; // ★ 추가: U_USER.U_TYPE 조회용
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCouponController {

    /*------------------coupon------------------*/
    private final AdminCouponService adminCouponService;
    private final CouponIssueService couponIssueService;
    private final UserMapper userMapper; // ★ 추가

    @GetMapping("/coupon/list")
    public String list(PageRequestDTO req, Model model) {
        if (req.getPg() <= 0) req.setPg(1);
        if (req.getSize() <= 0) req.setSize(10);

        PageResponseDTO<CouponDTO> page = adminCouponService.getCouponPage(req);

        // ★ 로그인 사용자 타입 조회하여 뷰에 전달 (모달 옵션 분기용)
        String loginId = currentUsername();
        String userType = (loginId != null ? userMapper.selectUserType(loginId) : null);

        model.addAttribute("page", page);
        model.addAttribute("req", req);
        model.addAttribute("userType", userType); // ★ 추가
        return "admin/coupon/list";
    }

    /** 등록 */
    @PostMapping("/coupon/register")
    public String register(@ModelAttribute CouponDTO dto, RedirectAttributes ra) {

        // 로그인 확인
        String loginId = currentUsername();
        if (isBlank(loginId)) {
            ra.addFlashAttribute("error", "로그인 후 이용하세요.");
            throw new AccessDeniedException("로그인 필요");
        }

        // ★ 발급처는 서버에서 강제 세팅
        dto.setIssuerUid(loginId);

        // ★ 사용자 타입에 따른 ctType 허용 검증
        String userType = userMapper.selectUserType(loginId);
        if (!isAllowedCtType(userType, dto.getCtType())) {
            ra.addFlashAttribute("error", "권한이 없는 쿠폰 종류입니다.");
            return "redirect:/admin/coupon/list";
        }

        // 혜택 분류
        String b = dto.getBenefit();
        if (b != null) {
            String trimmed = b.trim();
            if (trimmed.contains("배송")) {
                dto.setBenefitDelivery("무료");
                dto.setBenefitMoney(null);
            } else {
                dto.setBenefitMoney(trimmed);
                dto.setBenefitDelivery(null);
            }
        }

        // 필수값 검증
        if (isBlank(dto.getCtType()) ||
                isBlank(dto.getCName()) ||
                dto.getStartDay() == null ||
                dto.getEndDay() == null) {
            ra.addFlashAttribute("error", "필수 항목을 모두 입력하세요.");
            return "redirect:/admin/coupon/list";
        }

        // 날짜 유효성
        if (dto.getEndDay().isBefore(dto.getStartDay())) {
            ra.addFlashAttribute("error", "사용기간이 올바르지 않습니다.");
            return "redirect:/admin/coupon/list";
        }

        adminCouponService.registerCoupon(dto);
        ra.addFlashAttribute("msg", "쿠폰이 등록되었습니다.");
        return "redirect:/admin/coupon/list";
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    // 현재 로그인 username
    private String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;
        String name = auth.getName();
        return (name != null && !"anonymousUser".equalsIgnoreCase(name)) ? name : null;
    }

    // ★ 사용자 타입별 허용 쿠폰종류
    // U_USER.U_TYPE 실제 값에 맞게 문자열을 조정하세요.
    // 예) "ADMIN"/"SELLER"를 쓰면 아래 비교도 그에 맞춰 변경
    private boolean isAllowedCtType(String userType, String ctType) {
        if (ctType == null || userType == null) return false;
        // 표준화(양끝 공백 제거)
        String ut = userType.trim();
        String ct = ctType.trim();

        // 관리자: 주문상품할인, 배송비무료
        if ("관리자".equals(ut) || "ADMIN".equalsIgnoreCase(ut)) {
            return "주문상품할인".equals(ct) || "배송비무료".equals(ct);
        }
        // 판매자: 개별상품할인
        if ("판매자".equals(ut) || "SELLER".equalsIgnoreCase(ut)) {
            return "개별상품할인".equals(ct);
        }
        // 그 외(일반, 사용자 등) 불가
        return false;
    }

    @PostMapping("/coupon/close")
    @ResponseBody
    public ResponseEntity<?> close(@RequestParam String cId) {
        if (cId == null || cId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "msg", "쿠폰번호가 없습니다."));
        }
        int n = adminCouponService.closeCoupon(cId);
        if (n > 0) {
            return ResponseEntity.ok(Map.of("ok", true, "cId", cId));
        } else {
            return ResponseEntity.status(500).body(Map.of("ok", false, "msg", "종료 처리 실패"));
        }
    }

    @GetMapping("/coupon/detail")
    @ResponseBody
    public ResponseEntity<?> detail(@RequestParam String cId) {
        if (cId == null || cId.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "msg", "쿠폰번호가 없습니다."));
        }
        CouponDTO dto = adminCouponService.getCouponDetail(cId);
        if (dto == null) {
            return ResponseEntity.status(404).body(Map.of("ok", false, "msg", "쿠폰을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(Map.of("ok", true, "data", dto));
    }

    /*-------------------------*/
    /* 쿠폰 발급현황 */
    @GetMapping("/coupon/issued")
    public String issued(PageRequestDTO req, Model model) {
        if (req.getPg() <= 0) req.setPg(1);
        if (req.getSize() <= 0) req.setSize(10);

        if (req.getSearchType() != null) {
            switch (req.getSearchType()) {
                case "poNo", "cId", "cName", "userId" -> {}
                default -> req.setSearchType(null);
            }
        }

        PageResponseDTO<CouponIssueDTO> page = couponIssueService.getIssuePage(req);
        model.addAttribute("page", page);
        model.addAttribute("req", req);
        return "admin/coupon/issued";
    }

    @GetMapping("/coupon/issued/detail")
    @ResponseBody
    public ResponseEntity<?> issuedDetail(@RequestParam String poNo) {
        if (poNo == null || poNo.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "msg", "발급번호가 없습니다."));
        }
        CouponIssueDTO dto = couponIssueService.findByPoNo(poNo); // 구현 필요 시 Mapper 추가
        if (dto == null) {
            return ResponseEntity.status(404).body(Map.of("ok", false, "msg", "발급내역을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(Map.of("ok", true, "data", dto));
    }

    @PostMapping("/coupon/issued/stop")
    @ResponseBody
    public ResponseEntity<?> stopIssue(@RequestParam String poNo) {
        if (poNo == null || poNo.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "msg", "발급번호가 없습니다."));
        }

        int n = couponIssueService.stopIssue(poNo);
        if (n > 0) {
            return ResponseEntity.ok(Map.of("ok", true, "poNo", poNo));
        } else {
            return ResponseEntity.status(500).body(Map.of("ok", false, "msg", "발급 중단 처리 실패"));
        }
    }
}
