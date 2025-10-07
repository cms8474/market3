package kr.co.team3.admin_service;


import kr.co.team3.admin_dto.DashboardDTO;
import kr.co.team3.admin_repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository repo;

    public DashboardDTO stats() {
        var m = repo.findOpsCounts();
        return DashboardDTO.builder()
                .waiting(toLong(m.get("WAITING")))
                .ready(toLong(m.get("READY")))
                .cancel(toLong(m.get("CANCEL_REQ")))
                .exchange(toLong(m.get("EXCHANGE_REQ")))
                .returnA(toLong(m.get("RETURN_REQ")))
                .build();
    }

    public DashboardDTO totals() {
        var m = repo.findTotals();
        return DashboardDTO.builder()
                .orderCount(toLong(m.get("ORDER_COUNT")))
                .orderAmount(toLong(m.get("ORDER_AMOUNT")))
                .registerCount(toLong(m.get("REGISTER_COUNT")))
                .visitor(toLong(m.get("VISITOR")))
                .qna(toLong(m.get("QNA")))
                .build();
    }

    public DashboardDTO today() {
        LocalDate today = LocalDate.now();
        return range(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
    }

    public DashboardDTO yesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return range(yesterday.atStartOfDay(), yesterday.plusDays(1).atStartOfDay());
    }

    private DashboardDTO range(LocalDateTime from, LocalDateTime to) {
        var m = repo.findRangeTotals(from, to);
        return DashboardDTO.builder()
                .orderCount(toLong(m.get("ORDER_COUNT")))
                .orderAmount(toLong(m.get("ORDER_AMOUNT")))
                .registerCount(toLong(m.get("REGISTER_COUNT")))
                .visitor(toLong(m.get("VISITOR")))
                .qna(toLong(m.get("QNA")))
                .build();
    }

    public Map<String, Object> bar() {
        var rows = repo.findBar5Days();
        List<String> labels = new ArrayList<>();
        List<Long> orders = new ArrayList<>();
        List<Long> payments = new ArrayList<>();
        List<Long> cancels = new ArrayList<>();
        for (Object[] r : rows) {
            labels.add((String) r[0]);
            orders.add(toLong(r[1]));
            payments.add(toLong(r[2]));
            cancels.add(toLong(r[3]));
        }
        return Map.of(
                "labels", labels,
                "orders", orders,
                "payments", payments,
                "cancels", cancels
        );
    }

    public Map<String, Object> pie() {
        var rows = repo.findPieByCategory();
        List<String> labels = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        for (Object[] r : rows) {
            labels.add((String) r[0]);
            values.add(toLong(r[1]));
        }
        return Map.of("labels", labels, "values", values);
    }

    private long toLong(Object v) {
        if (v == null) return 0L;
        if (v instanceof Number n) return n.longValue();
        if (v instanceof BigDecimal bd) return bd.longValue();
        return Long.parseLong(v.toString());
    }
}