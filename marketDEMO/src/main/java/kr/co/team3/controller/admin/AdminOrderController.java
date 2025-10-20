package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.OrderDetailDTO;
import kr.co.team3.admin_dto.OrderStatusDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.OrderDetailService;
import kr.co.team3.admin_service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminOrderController {
    /*------------------order------------------*/
    private final OrderStatusService service;
    private final OrderDetailService orderDetailService;

    @GetMapping("/order/list")
    public String orders(PageRequestDTO pageRequestDTO, Model model) {
        log.info("▶ [OrderStatus] page request: {}", pageRequestDTO);

        // 서비스 호출
        PageResponseDTO<OrderStatusDTO> pageResponseDTO = service.selectAll(pageRequestDTO);

        // 모델에 담기
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "admin/order/list";
    }

    @GetMapping("/order/detail")
    public ResponseEntity<?> getOrderDetail(@RequestParam String poNo) {
        log.info("GET /admin/order/detail poNo={}", poNo);

        if (poNo == null || poNo.isBlank()) {
            return ResponseEntity.badRequest().body("poNo is required");
        }

        OrderDetailDTO dto = orderDetailService.getOrderDetail(poNo);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    /** 배송입력 모달 프리필 (JSON) */
    @GetMapping("/order/ship/prefill")
    @ResponseBody
    public ResponseEntity<OrderDetailDTO> getShipPrefill(@RequestParam("poNo") String poNo) {
        OrderDetailDTO dto = orderDetailService.getShipPrefill(poNo);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    /** 배송 등록 */
    @PostMapping("/order/ship")
    public String registerShipment(@ModelAttribute @Validated OrderDetailDTO dto,
                                   RedirectAttributes ra) {
        orderDetailService.registerShipment(dto);
        ra.addFlashAttribute("msg", "배송정보가 등록되었습니다.");
        return "redirect:/admin/order/list";
    }




/*-----------------------------------------------------------------*/
    @GetMapping(value = {"/order/delivery"})
    public String orderdelivery() {
        System.out.println("go order/delivery");
        return "/admin/order/delivery";
    }

}
