package kr.co.team3.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.team3.admin_dto.RecruitmentDTO;
import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_service.RecruitmentService;
import kr.co.team3.product_dto.CsDTO;
import kr.co.team3.product_service.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/cs")
@RequiredArgsConstructor
public class AdminCsController {

    private final RecruitmentService recruitmentService;
    private final CsService csService;

    /*------------------ Notice ------------------*/
/*
    @GetMapping("/notice/list")
    public String noticeList(@RequestParam(required = false) String q,
                             @RequestParam(name = "cate", required = false) String catePrefix, // ex) noti01
                             @PageableDefault(size = 10, sort = "boardRegDate") Pageable pageable,
                             Model model) {
        Page<CsDTO> page = (q != null || catePrefix != null)
                ? csService.searchByPrefix("noti", catePrefix, q, pageable)
                : csService.getListByPrefix("noti", pageable);

        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        return "admin/cs/notice/noticeList";
    }
*/


    @GetMapping("/notice/modify")
    public String noticeModify() {
        log.info("go cs/noticeModify");
        return "admin/cs/notice/noticeModify";
    }

    @GetMapping("/notice/write")
    public String noticeWrite() {
        log.info("go cs/noticeWrite");
        return "admin/cs/notice/noticeWrite";
    }

    @GetMapping("/notice/view")
    public String noticeView() {
        log.info("go cs/noticeView");
        return "admin/cs/notice/noticeView";
    }


    /*------------------ FAQ ------------------*/
    @GetMapping("/faq/list")
    public String faqList() {
        log.info("go cs/faqList");
        return "admin/cs/faq/faqList";
    }

    @GetMapping("/faq/modify")
    public String faqModify() {
        log.info("go cs/faqModify");
        return "admin/cs/faq/faqModify";
    }

    @GetMapping("/faq/write")
    public String faqWrite() {
        log.info("go cs/faqWrite");
        return "admin/cs/faq/faqWrite";
    }

    @GetMapping("/faq/view")
    public String faqView() {
        log.info("go cs/faqView");
        return "admin/cs/faq/faqView";
    }


    /*------------------ QnA ------------------*/
    @GetMapping("/qna/list")
    public String qnaList() {
        log.info("go cs/qnaList");
        return "admin/cs/qna/qnaList";
    }

    @GetMapping("/qna/view")
    public String qnaView() {
        log.info("go cs/qnaView");
        return "admin/cs/qna/qnaView";
    }

    @GetMapping("/qna/answer")
    public String qnaAnswer() {
        log.info("go cs/qnaAnswer");
        return "admin/cs/qna/qnaAnswer";
    }


    /*------------------ Recruit ------------------*/
    @GetMapping("/recruit/list")
    public String recruitList(Model model) {
        List<Recruitment> list = recruitmentService.findAll();

        log.info("recruit list size = {}", list.size());
        model.addAttribute("list", list);
        return "admin/cs/recruit/recruitList";
    }

    @PostMapping("/recruit/register")
    public String recruitRegister(RecruitmentDTO recruitmentDTO) {
        log.info("recruitmentDTO = {}", recruitmentDTO);
        recruitmentService.save(recruitmentDTO);
        return "redirect:/kmarket/admin/cs/recruit/list";
    }


}
