package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.CouponIssueDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.AdminCouponService;
import kr.co.team3.admin_service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCouponController {

    /*------------------coupon------------------*/
    private final AdminCouponService adminCouponService;
    private final CouponIssueService couponIssueService;

    @GetMapping("/coupon/list")
    public String list(PageRequestDTO req, Model model) {
        // 방어코드 (음수 등)
        if (req.getPg() <= 0) req.setPg(1);
        if (req.getSize() <= 0) req.setSize(10);

        PageResponseDTO<CouponDTO> page = adminCouponService.getCouponPage(req);

        model.addAttribute("page", page);
        model.addAttribute("req", req);
        return "admin/coupon/list";
    }

    /** 등록 */
    @PostMapping("/coupon/register")
    public String register(@ModelAttribute CouponDTO dto, RedirectAttributes ra) {
        // 필수값 체크 (발급처, 종류, 이름, 혜택, 기간)
        if (isBlank(dto.getIssuerUid()) ||
                isBlank(dto.getCtType()) ||
                isBlank(dto.getCName())  ||
                isBlank(dto.getBenefit())||
                dto.getStartDay() == null ||
                dto.getEndDay() == null) {
            ra.addFlashAttribute("error", "필수 항목을 모두 입력하세요.");
            return "redirect:/admin/coupon/list";
        }
        // 기간 유효성
        if (dto.getEndDay().isBefore(dto.getStartDay())) {
            ra.addFlashAttribute("error", "사용기간이 올바르지 않습니다. (종료일이 시작일보다 빠름)");
            return "redirect:/admin/coupon/list";
        }

        if (isBlank(dto.getIssuerName())) {
            dto.setIssuerName(dto.getIssuerUid());
        }

        adminCouponService.registerCoupon(dto);
        ra.addFlashAttribute("msg", "쿠폰이 등록되었습니다.");
        return "redirect:/admin/coupon/list";
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
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
    /*쿠폰 발급현황*/
    // 발급현황 목록 (뷰)
    @GetMapping("/coupon/issued")
    public String issued(PageRequestDTO req, Model model) {
        // 방어코드
        if (req.getPg() <= 0) req.setPg(1);
        if (req.getSize() <= 0) req.setSize(10);

        // searchType 허용값 제한 (발급번호/쿠폰번호/쿠폰명/사용자)
        if (req.getSearchType() != null) {
            switch (req.getSearchType()) {
                case "poNo", "cId", "cName", "userId" -> {}
                default -> req.setSearchType(null); // 잘못된 값이면 무시
            }
        }

        PageResponseDTO<CouponIssueDTO> page = couponIssueService.getIssuePage(req);
        model.addAttribute("page", page);
        model.addAttribute("req", req);
        return "admin/coupon/issued"; // templates/admin/coupon/issued.html
    }


    // 발급 단건 상세
    @GetMapping("/coupon/issued/detail")
    @ResponseBody
    public ResponseEntity<?> issuedDetail(@RequestParam String poNo) {
        if (poNo == null || poNo.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "msg", "발급번호가 없습니다."));
        }
        // 서비스에 findByPoNo가 없다면 Mapper에 하나 더 만들어도 됨
        CouponIssueDTO dto = couponIssueService.findByPoNo(poNo); // <- 필요 시 구현
        if (dto == null) {
            return ResponseEntity.status(404).body(Map.of("ok", false, "msg", "발급내역을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(Map.of("ok", true, "data", dto));
    }




}
