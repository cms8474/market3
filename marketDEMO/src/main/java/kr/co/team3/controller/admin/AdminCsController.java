package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.RecruitmentDTO;
import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("kmarket/admin/cs")
@RequiredArgsConstructor
public class AdminCsController {

    private final RecruitmentService recruitmentService;

    /*------------------ Notice ------------------*/
    @GetMapping("/notice/list")
    public String noticeList() {
        log.info("go cs/noticeList");
        return "admin/cs/notice/noticeList";
    }

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
        log.info("go cs/recruitList");

        List<Recruitment> list = recruitmentService.findAll();

        log.info("recruit list size = {}", list.size());
        model.addAttribute("list", list);
        return "admin/cs/recruit/recruitList";
    }

}
