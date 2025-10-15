package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.DashboardDTO;
import kr.co.team3.admin_service.DashboardService;
import kr.co.team3.admin_service.NoticeService;
import kr.co.team3.admin_service.QnaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DashboardService service;

    public AdminController(DashboardService service) {
        this.service = service;
    }
    @GetMapping({"", "/"})
    public String admin(Model model) {
        var stats          = dashboardService.stats();
        var statsTotal     = dashboardService.totals();
        var statsToday     = dashboardService.today();
        var statsYesterday = dashboardService.yesterday();
        var bar            = dashboardService.bar();
        var pie            = dashboardService.pie();

        model.addAttribute("stats",          stats);
        model.addAttribute("statsTotal",     statsTotal);
        model.addAttribute("statsToday",     statsToday);
        model.addAttribute("statsYesterday", statsYesterday);
        model.addAttribute("bar",            bar);
        model.addAttribute("pie",            pie);

        model.addAttribute("noticeTop5",     noticeService.latest5());
        model.addAttribute("qnaTop5",        qnaService.latest5());
        model.addAttribute("contentFragment","inc/admin/admin_main :: content");
        model.addAttribute("pageTitle",      "관리자 대시보드");
        model.addAttribute("activeMenu",     "dashboard");
        return "admin";
    }


}