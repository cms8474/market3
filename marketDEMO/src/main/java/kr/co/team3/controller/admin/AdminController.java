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
    public String adminMain(Model model) {
        var S = new java.util.LinkedHashMap<String, Object>();
        S.put("waiting", 0);
        S.put("ready", 0);
        S.put("cancel", 0);
        S.put("exchange", 0);
        S.put("returnA", 0);
        model.addAttribute("stats", S);

        // 2) 오늘/어제/전체 지표 (DTO를 그대로 쓰는 경우)
        LocalDate today = LocalDate.now();
        DashboardDTO TD = service.stats(today, today);
        DashboardDTO YD = service.stats(today.minusDays(1), today.minusDays(1));
        DashboardDTO TOT = service.stats(today.minusDays(29), today); // 예: 최근 30일 누적
        model.addAttribute("statsToday", TD);
        model.addAttribute("statsYesterday", YD);
        model.addAttribute("statsTotal", TOT);

        // 3) 차트 데이터가 템플릿에서 필요하다면(스크립트에서 BAR/PIE 참조)
        model.addAttribute("bar", java.util.Map.of(
                "labels", java.util.List.of(),
                "orders", java.util.List.of(),
                "payments", java.util.List.of(),
                "cancels", java.util.List.of()
        ));
        model.addAttribute("pie", java.util.Map.of(
                "labels", java.util.List.of(),
                "values", java.util.List.of()
        ));

        // 4) 공지/문의 리스트 비어있을 때도 NPE 안 나게
        model.addAttribute("noticeTop5", java.util.List.of());
        model.addAttribute("qnaTop5", java.util.List.of());

        return "inc/admin/admin_main"; // ← 파일 경로에 맞춤
    }

    @ResponseBody
    @GetMapping("/dashboard")
    public DashboardDTO getDashboard(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return service.stats(from, to);
    }

    @ResponseBody
    @GetMapping("/dashboard/today")
    public DashboardDTO today() {
        LocalDate d = LocalDate.now();
        return service.stats(d, d);
    }

    @ResponseBody
    @GetMapping("/dashboard/last7d")
    public DashboardDTO last7d() {
        LocalDate to = LocalDate.now();
        LocalDate from = to.minusDays(6);
        return service.stats(from, to);
    }
}