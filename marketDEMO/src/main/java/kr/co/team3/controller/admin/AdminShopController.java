package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.SalesSummaryDTO;
import kr.co.team3.admin_mapper.AdminShopMapper;
import kr.co.team3.admin_service.AdminShopService;
import kr.co.team3.admin_service.SalesSummaryService;
import kr.co.team3.product_dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminShopController {

    private final AdminShopService  adminShopService;
    private final SalesSummaryService salesSummaryService;

    /*------------------shop--------------------*/
    /* shop 목록 조회*/
    @GetMapping(value = {"/shop/list"})
    public String shoplist(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO pageResponseDTO = adminShopService.selectAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);

        return "admin/shop/list";
    }


    /* 상점 등록  */
    @PostMapping("/shop/register")
    public String registerShop(@ModelAttribute @Validated MemberDTO dto,
                               Model model) {
        try {
            log.info(">>> registerShop(): {}", dto);
            adminShopService.registerShop(dto);
            // 성공 시 목록으로 리다이렉트
            return "redirect:/admin/shop/list";
        } catch (IllegalArgumentException e) {
            log.warn(">>> Validation error: {}", e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("pageResponseDTO",
                    adminShopService.selectAll(new PageRequestDTO())); // 목록 다시 띄움
            return "admin/shop/list";
        } catch (Exception e) {
            log.error(">>> Register error", e);
            model.addAttribute("errorMessage", "상점 등록 중 오류가 발생했습니다.");
            model.addAttribute("pageResponseDTO",
                    adminShopService.selectAll(new PageRequestDTO()));
            return "admin/shop/list";
        }
    }

    /*삭제*/
    @PostMapping("/shop/delete")
    public String delete(@RequestParam("ids") List<String> ids, RedirectAttributes ra) {
        log.info(">>> delete ids={}", ids);
        int n = adminShopService.deleteShops(ids);
        ra.addFlashAttribute("msg", n + "건 삭제되었습니다.");
        return "redirect:/admin/shop/list";
    }


    @PostMapping("/shop/state")
    @ResponseBody
    public ResponseEntity<?> changeState(
            @RequestParam("u_id") String uId,
            @RequestParam("action") String action) {

        log.info("state change u_id={}, action={}", uId, action);

        boolean ok;
        String newState;

        switch (action) {
            case "approve": // 운영준비 → 운영
                ok = adminShopService.approve(uId);
                newState = "운영";
                break;
            case "stop":    // 운영 → 운영중지
                ok = adminShopService.stop(uId);
                newState = "운영중지";
                break;
            case "resume":  // 운영중지 → 운영
                ok = adminShopService.resume(uId);
                newState = "운영";
                break;
            default:
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "unknown action"
                ));
        }

        if (!ok) {
            // 조건 미일치로 업데이트 0건 = 잘못된 현재 상태
            return ResponseEntity.status(409).body(Map.of(
                    "success", false,
                    "message", "상태 전이가 유효하지 않습니다."
            ));
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "newState", newState
        ));
    }


    /* 매출현황 */
    @GetMapping("/shop/sales")
    public String shopSales(PageRequestDTO pageRequestDTO,
                            @RequestParam(required = false) String baseWeek,   // ex) 2025-W42
                            @RequestParam(required = false) String baseMonth,  // ex) 2025-10
                            Model model) {


        log.info("[REQ] periodType={}, baseDate={}, baseWeek={}, baseMonth={}, pg={}, size={}, sort={}, dir={}",
                pageRequestDTO.getPeriodType(), pageRequestDTO.getBaseDate(), baseWeek, baseMonth,
                pageRequestDTO.getPg(), pageRequestDTO.getSize(), pageRequestDTO.getSort(), pageRequestDTO.getDir());


        // ==== 1. 기본값 보정 ====
        if (pageRequestDTO.getSort() == null || pageRequestDTO.getSort().isBlank()) {
            pageRequestDTO.setSort("sales_sum");
        }
        if (pageRequestDTO.getDir() == null || pageRequestDTO.getDir().isBlank()) {
            pageRequestDTO.setDir("desc");
        }
        if (pageRequestDTO.getPeriodType() == null || pageRequestDTO.getPeriodType().isBlank()) {
            pageRequestDTO.setPeriodType("day");
        }

        // ==== 2. 기간 타입별 입력값 정규화 ====
        switch (pageRequestDTO.getPeriodType()) {
            case "week" -> {
                if (baseWeek != null && !baseWeek.isBlank()) {
                    // "2025-W42" → 해당 주의 월요일 구하기
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("YYYY-'W'ww-e");
                    LocalDate monday = LocalDate.parse(baseWeek + "-1", fmt);
                    // 해당 주의 마지막날(일요일)을 baseDate로 사용
                    pageRequestDTO.setBaseDate(monday.plusDays(6));
                }
            }
            case "month" -> {
                if (baseMonth != null && !baseMonth.isBlank()) {
                    // "2025-10" → 2025년 10월의 마지막날
                    YearMonth ym = YearMonth.parse(baseMonth);
                    pageRequestDTO.setBaseDate(ym.atEndOfMonth());
                }
            }
            default -> {
            }
        }

        log.info("[NORM] periodType={}, baseDate={}", pageRequestDTO.getPeriodType(), pageRequestDTO.getBaseDate());

        // ==== 3. 서비스 호출 ====
        PageResponseDTO<SalesSummaryDTO> pageResponseDTO = salesSummaryService.getList(pageRequestDTO);

        log.info("[RES] total={}, items={}", pageResponseDTO.getTotal(),
                (pageResponseDTO.getDtoList() != null ? pageResponseDTO.getDtoList().size() : 0));


        // ==== 4. 모델 바인딩 ====
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("list", pageResponseDTO.getDtoList());

        return "admin/shop/sales";
    }
}



