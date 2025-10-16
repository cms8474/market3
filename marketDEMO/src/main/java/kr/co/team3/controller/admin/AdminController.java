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
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
@RequestMapping("/kmarket/admin")
public class AdminController {
    private final DashboardService service;
    //private final NoticeService noticeService;
    //private final QnaService qnaService;

    public AdminController(DashboardService service) {
        this.service = service;
        //this.noticeService = noticeService;
        //this.qnaService = qnaService;
    }
    @GetMapping({"", "/"})
    public String adminMain(Model model) {
        LocalDate today = LocalDate.now();

        DashboardDTO TD  = service.stats(today, today);
        DashboardDTO YD  = service.stats(today.minusDays(1), today.minusDays(1));
        DashboardDTO TOT = service.stats(today.minusDays(29), today);

        java.util.function.Function<Integer,Integer> nzi = v -> v == null ? 0 : v;
        java.util.function.Function<Long,Long>       nzl = v -> v == null ? 0L: v;

        // 2) 상단 운영현황에 들어갈 S 맵
        Map<String, Object> S = new LinkedHashMap<>();
        int depositDone = nzi.apply(TD != null ? TD.getDepositDone() : 0);
        int shippingCnt = nzi.apply(TD != null ? TD.getShippingCnt() : 0);
        int canceledCnt = nzi.apply(TD != null ? TD.getCanceledCnt() : 0);
        int exchangeCnt = nzi.apply(TD != null ? TD.getExchangeCnt() : 0);
        int returnCnt   = nzi.apply(TD != null ? TD.getReturnCnt()   : 0);

        S.put("depositDone", depositDone);
        S.put("shippingCnt", shippingCnt);
        S.put("canceledCnt", canceledCnt);
        S.put("exchangeCnt", exchangeCnt);
        S.put("returnCnt",   returnCnt);
        
        // 전에 해놨던거 이름 안맞아서 임의로 넣어둠 (
        S.put("waiting",  depositDone);
        S.put("ready",    shippingCnt);
        S.put("cancel",   canceledCnt);
        S.put("exchange", exchangeCnt);
        S.put("returnA",  returnCnt);

        model.addAttribute("stats", S);
        model.addAttribute("statsToday", TD);
        model.addAttribute("statsYesterday", YD);
        model.addAttribute("statsTotal", TOT);

        // 3) 차트 데이터 (최근 7일)
        LocalDate from = today.minusDays(6);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd");

        List<String> labels   = new ArrayList<>();
        List<Integer> orders  = new ArrayList<>();
        List<Long>    payments= new ArrayList<>();
        List<Integer> cancels = new ArrayList<>();

        for (LocalDate d = from; !d.isAfter(today); d = d.plusDays(1)) {
            DashboardDTO day = service.stats(d, d);
            labels.add(d.format(fmt));
            orders.add(  day != null ? nzi.apply(day.getOrderCnt())     : 0);
            payments.add(day != null ? nzl.apply(day.getOrderAmt())     : 0L); // 원 단위
            cancels.add( day != null ? nzi.apply(day.getCanceledCnt())  : 0);
        }

        model.addAttribute("bar", Map.of(
                "labels",   labels,
                "orders",   orders,
                "payments", payments,
                "cancels",  cancels
        ));

        // 4) 파이차트(없으면 빈 값)
        model.addAttribute("pie", Map.of(
                        "labels", List.of(),
                        "values", List.of()
        ));

        // 5) 공지/문의 리스트 (비어있어도 안전)
        model.addAttribute("noticeTop5", List.of());
        model.addAttribute("qnaTop5",    List.of());

        // 6) 레이아웃 템플릿 본문 프래그먼트 지정
        model.addAttribute("contentFragment", "inc/admin/admin_main :: content");
        return "inc/admin/admin_template";
    }


    @ResponseBody
    @GetMapping("/dashboard")
    public DashboardDTO getDashboard(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from ,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {
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