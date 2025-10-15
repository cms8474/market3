package kr.co.team3.admin_repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.co.team3.admin_repository.DashboardRepository;
import jakarta.persistence.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String SQL = """
        WITH O AS (
          SELECT po.po_no, po.po_state, po.po_tot_price, po.po_orderDate
          FROM PRODUCT_ORDER po
          WHERE TRUNC(po.po_orderDate) BETWEEN TRUNC(:fromDate) AND TRUNC(:toDate)
        ),
        OI AS (
          SELECT oi.oi_po_no, oi.oi_del_status
          FROM ORDER_ITEMS oi
          WHERE oi.oi_po_no IN (SELECT po_no FROM O)
        ),
        UU AS (
          SELECT COUNT(*) AS signup_cnt
          FROM U_USER u
          WHERE TRUNC(u.u_create_day) BETWEEN TRUNC(:fromDate) AND TRUNC(:toDate)
        ),
        BD AS (
          SELECT COUNT(*) AS board_cnt
          FROM BOARD b
          WHERE TRUNC(b.b_reg_date) BETWEEN TRUNC(:fromDate) AND TRUNC(:toDate)
        )
        SELECT
          NVL(SUM(CASE WHEN o.po_state = '입금완료' THEN 1 ELSE 0 END), 0) AS deposit_done,
          NVL(SUM(CASE WHEN o.po_state = '취소'     THEN 1 ELSE 0 END), 0) AS canceled_cnt,
          NVL(SUM(CASE WHEN o.po_state = '교환'     THEN 1 ELSE 0 END), 0) AS exchange_cnt,
          NVL(SUM(CASE WHEN o.po_state = '반품'     THEN 1 ELSE 0 END), 0) AS return_cnt,
          NVL(SUM(CASE WHEN oi.oi_del_status IN ('상품준비중','배송준비','배송중','배송완료') THEN 1 ELSE 0 END), 0) AS shipping_cnt,
          NVL(COUNT(DISTINCT o.po_no), 0) AS order_cnt,
          NVL(SUM(o.po_tot_price), 0)     AS order_amt,
          (SELECT signup_cnt FROM UU) AS signup_cnt,
          0 AS visit_cnt,
          (SELECT board_cnt  FROM BD) AS board_cnt
        FROM O o
        LEFT JOIN OI oi ON oi.oi_po_no = o.po_no
        """;

    @Override
    public Object[] loadDashboardRaw(LocalDate fromDate, LocalDate toDate) {
        var q = em.createNativeQuery(SQL);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate",   toDate);
        return (Object[]) q.getSingleResult();
    }
}