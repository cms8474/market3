package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.AdminShopMapper;
import kr.co.team3.admin_service.AdminShopService;
import kr.co.team3.product_dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminShopController {

    private final AdminShopService  adminShopService;

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



    /*매출현황*/
    @GetMapping(value = {"/shop/sales"})
    public String shopsales(){
        System.out.println("go shopsales");
        return "admin/shop/sales";
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
}

