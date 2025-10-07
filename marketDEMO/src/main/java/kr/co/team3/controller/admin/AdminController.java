package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.DashboardDTO;
import kr.co.team3.admin_service.DashboardService;
import kr.co.team3.admin_service.NoticeService;
import kr.co.team3.admin_service.QnaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("kmarket/admin")
public class AdminController {
    private final DashboardService dashboardService;
    private final NoticeService noticeService;
    private final QnaService qnaService;

    public AdminController(DashboardService dashboardService, NoticeService noticeService, QnaService qnaService) {
        this.dashboardService = dashboardService;
        this.noticeService = noticeService;
        this.qnaService = qnaService;
    }

    @GetMapping({"", "/"})
    public String admin(Model model) {


        var stats = dashboardService.stats();
        if (stats == null) {
            stats = DashboardDTO.builder()
                    .waiting(0).ready(0).cancel(0).exchange(0).returnA(0)
                    .orderCount(0).orderAmount(0).registerCount(0).visitor(0).qna(0)
                    .build();
        }

        model.addAttribute("stats",        dashboardService.stats());
        model.addAttribute("statsTotal",   dashboardService.totals());
        model.addAttribute("statsToday",   dashboardService.today());
        model.addAttribute("statsYesterday", dashboardService.yesterday());
        model.addAttribute("bar",          dashboardService.bar());
        model.addAttribute("pie",          dashboardService.pie());


        model.addAttribute("noticeTop5", noticeService.latest5());
        model.addAttribute("qnaTop5", qnaService.latest5());

        model.addAttribute("contentFragment", "inc/admin/admin_main :: content");


        model.addAttribute("pageTitle", "관리자 대시보드");
        model.addAttribute("activeMenu", "dashboard");
        return "admin";
    }
}