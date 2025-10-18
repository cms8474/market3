package kr.co.team3.controller.admin;


import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_service.AdminMemberService;
import kr.co.team3.admin_service.AdminPointHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
