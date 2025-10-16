package kr.co.team3.admin_repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.co.team3.admin_repository.DashboardRepository;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Object[] loadDashboardRaw(LocalDate from, LocalDate to) {

        String sql = """
            WITH
            O AS (
              SELECT po.po_no, po.po_state, po.po_tot_price, po.po_orderDate
              FROM PRODUCT_ORDER po
              WHERE TRUNC(po.po_orderDate) BETWEEN TRUNC(:fromDate) AND TRUNC(:toDate)
            ),
            OI AS (
              SELECT oi.oi_po_no, oi.oi_del_status
              FROM ORDER_ITEMS oi
              WHERE oi.oi_po_no IN (SELECT po_no FROM O)
            ),
            OJOIN AS (
              SELECT
                SUM(CASE WHEN o.po_state='입금완료' THEN 1 ELSE 0 END) AS deposit_done,
                SUM(CASE WHEN o.po_state='취소'     THEN 1 ELSE 0 END) AS canceled_cnt,
                SUM(CASE WHEN o.po_state='교환'     THEN 1 ELSE 0 END) AS exchange_cnt,
                SUM(CASE WHEN o.po_state='반품'     THEN 1 ELSE 0 END) AS return_cnt,
                SUM(CASE WHEN oi.oi_del_status IN ('상품준비중','배송준비','배송중','배송완료') THEN 1 ELSE 0 END) AS shipping_cnt,
                COUNT(DISTINCT o.po_no) AS order_cnt,
                SUM(o.po_tot_price)     AS order_amt
              FROM O o
              LEFT JOIN OI oi ON oi.oi_po_no = o.po_no
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
              NVL(oj.deposit_done, 0) AS deposit_done,
              NVL(oj.canceled_cnt, 0) AS canceled_cnt,
              NVL(oj.exchange_cnt, 0) AS exchange_cnt,
              NVL(oj.return_cnt,   0) AS return_cnt,
              NVL(oj.shipping_cnt, 0) AS shipping_cnt,
              NVL(oj.order_cnt,    0) AS order_cnt,
              NVL(oj.order_amt,    0) AS order_amt,
              uu.signup_cnt        AS signup_cnt,
              0                    AS visit_cnt,   -- 필요 시 VL CTE로 교체
              bd.board_cnt         AS board_cnt
            FROM OJOIN oj, UU uu, BD bd
            """;

        Query q = em.createNativeQuery(sql);
        q.setParameter("fromDate", Timestamp.valueOf(from.atStartOfDay()));
        q.setParameter("toDate",   Timestamp.valueOf(to.plusDays(1).atStartOfDay().minusSeconds(1)));
        return (Object[]) q.getSingleResult();
    }
}