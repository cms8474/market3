package kr.co.team3.admin_service;


import jakarta.transaction.Transactional;
import kr.co.team3.admin_dto.DashboardDTO;
import kr.co.team3.admin_repository.DashboardRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class DashboardService {
    private final DashboardRepository repo;

    public DashboardService(DashboardRepository repo) {
        this.repo = repo;
    }

    public DashboardDTO stats(LocalDate from, LocalDate to) {
        Object[] row = repo.loadDashboardRaw(from, to);
        // 순서: deposit_done, canceled_cnt, exchange_cnt, return_cnt, shipping_cnt,
        //       order_cnt, order_amt, signup_cnt, visit_cnt(항상 0), board_cnt
        return DashboardDTO.builder()
                .depositDone(((Number) row[0]).intValue())
                .canceledCnt(((Number) row[1]).intValue())
                .exchangeCnt(((Number) row[2]).intValue())
                .returnCnt(((Number) row[3]).intValue())
                .shippingCnt(((Number) row[4]).intValue())
                .orderCnt(((Number) row[5]).intValue())
                .orderAmt(((Number) row[6]).longValue())
                .signupCnt(((Number) row[7]).intValue())
                .visitCnt(((Number) row[8]).intValue())
                .boardCnt(((Number) row[9]).intValue())
                .from(from).to(to)
                .build();
    }
}