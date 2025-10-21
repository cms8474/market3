package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.*;
import kr.co.team3.admin_mapper.ShipStatusMapper;
import kr.co.team3.admin_mapper.UserMapper;
import kr.co.team3.admin_service.OrderDetailService;
import kr.co.team3.admin_service.OrderStatusService;
import kr.co.team3.admin_service.ShipStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminOrderController {
    /*------------------order------------------*/
    private final OrderStatusService service;
    private final OrderDetailService orderDetailService;
    private final ShipStatusService shipStatusService;


    private final UserMapper userMapper;
    private final ShipStatusMapper shipStatusMapper;

    @GetMapping("/order/list")
    public String orders(PageRequestDTO pageRequestDTO, Model model, Principal principal) {
        log.info("▶ [OrderStatus] page request: {}", pageRequestDTO);

        // 로그인 정보 → req에 주입
        String loginId = (principal != null) ? principal.getName() : null;
        String userType = (loginId != null) ? userMapper.selectUserType(loginId) : null;
        pageRequestDTO.setSellerId(loginId); // 판매자 아이디(=U_ID)
        pageRequestDTO.setUserType(userType); // '관리자'/'판매자'/'일반'

        PageResponseDTO<OrderStatusDTO> pageResponseDTO = service.selectAll(pageRequestDTO);

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

    @Scheduled(cron = "0 0 3 * * *")
    public void updateShipStatusDaily() {
        int count = service.runAutoUpdate();
        log.info("[배송상태 자동갱신] {}건 상태 업데이트 완료", count);
    }


/*-----------------------------------------------------------------*/


    @GetMapping("/order/delivery")
    public String shipList(PageRequestDTO req, Model model, Principal principal) {

        // 기본 페이지 세팅
        if (req.getPg() < 1) req.setPg(1);
        if (req.getSize() < 1) req.setSize(10);

        // 로그인 사용자 정보 세팅
        String loginId  = (principal != null) ? principal.getName() : null;
        String userType = (loginId != null) ? userMapper.selectUserType(loginId) : null;
        req.setSellerId(loginId);   // 판매자 아이디
        req.setUserType(userType);  // 관리자 / 판매자 / 일반

        // 서비스 호출
        PageResponseDTO<ShipStatusDTO> page = shipStatusService.getPage(req);

        // 뷰로 전달
        model.addAttribute("pageResponseDTO", page);
        model.addAttribute("pageRequestDTO", req);

        return "admin/order/delivery";
    }


    @GetMapping("/order/delivery/detail")
    @ResponseBody
    public ResponseEntity<?> getDetail(
            @RequestParam(required = false) String poNo,
            @RequestParam(required = false) String trackingNum) {

        OrderDeliveryDetailDTO dto = shipStatusService.getDetail(poNo, trackingNum);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
