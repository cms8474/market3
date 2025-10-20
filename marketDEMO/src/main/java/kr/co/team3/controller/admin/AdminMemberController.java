package kr.co.team3.controller.admin;


import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.AdminMemberService;
import kr.co.team3.admin_service.AdminPointHistoryService;
import kr.co.team3.product_dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMemberController {
    private final AdminMemberService memberService;
    private final AdminPointHistoryService pointService;


    /*------------------member------------------*/
    @GetMapping(value = {"/member/list"})
    public String memberlist(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO pageResponseDTO = memberService.selectAll(pageRequestDTO);
        model.addAttribute(pageResponseDTO);

        return "admin/member/list";
    }



    // 수정 상세
    @GetMapping("/detail")
    @ResponseBody
    public ResponseEntity<?> detail(@RequestParam("u_id") String uId) {
        MemberDTO dto = memberService.selectMemberById(uId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    //수정 단건
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@ModelAttribute MemberDTO dto) {
        if (dto.getU_id() == null || dto.getU_id().isBlank()) {
            return ResponseEntity.badRequest().body("u_id is required");
        }

        log.info(">>> UPDATE REQ dto={}", dto);

        try {
            memberService.updateMember(dto);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            log.error("update error for {}: {}", dto.getU_id(), e.getMessage(), e);
            return ResponseEntity.internalServerError().body("FAIL");
        }
    }

    // 상태 업데이트
    @PostMapping("/member/status/stop")
    @ResponseBody
    public ResponseEntity<?> stop(@RequestParam("u_id") String uId) {
        return memberService.stop(uId) ? ResponseEntity.ok("OK") : ResponseEntity.badRequest().body("NO_UPDATE");
    }

    @PostMapping("/member/status/resume")
    @ResponseBody
    public ResponseEntity<?> resume(@RequestParam("u_id") String uId) {
        return memberService.resume(uId) ? ResponseEntity.ok("OK") : ResponseEntity.badRequest().body("NO_UPDATE");
    }

    @PostMapping("/member/status/deactivate")
    @ResponseBody
    public ResponseEntity<?> deactivate(@RequestParam("u_id") String uId) {
        return memberService.deactivate(uId) ? ResponseEntity.ok("OK") : ResponseEntity.badRequest().body("NO_UPDATE");
    }

    //선탣수정
    @PostMapping("/member/bulk/rank")
    @ResponseBody
    public ResponseEntity<?> bulkRank(@RequestBody List<MemberDTO> updates) {
        if (updates == null || updates.isEmpty()) {
            return ResponseEntity.badRequest().body("NO_UPDATES");
        }
        try {
            int rows = memberService.bulkUpdateRanks(updates);
            return ResponseEntity.ok("OK:" + rows);
        } catch (Exception e) {
            log.error("bulk rank update error", e);
            return ResponseEntity.internalServerError().body("FAIL");
        }
    }





    /*---------------------*/
    /*포인트----------------*/
    @GetMapping(value = {"/member/point"})
    public String point(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO pageResponseDTO = pointService.selectAll(pageRequestDTO);

        model.addAttribute(pageResponseDTO);
        return "admin/member/point";
    }

    @PostMapping("/member/point/delete")
    public String deletePointHistory(@RequestParam("uhNo") List<String> uhNoList) {
        log.info(">>> delete request. list={}", uhNoList);
        pointService.deletePointHistoryList(uhNoList);
        return "redirect:/admin/member/point";
    }

}
