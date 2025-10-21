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
            O_AGG AS (
              SELECT
                /* 주문 테이블만 보고 주문 단위 집계 */
                COUNT(CASE WHEN po_state='구매확정'    THEN 1 END) AS confirm_done,
                COUNT(CASE WHEN po_state='취소'     THEN 1 END) AS canceled_cnt,
                COUNT(CASE WHEN po_state='교환'     THEN 1 END) AS exchange_cnt,
                COUNT(CASE WHEN po_state='반품'     THEN 1 END) AS return_cnt,
                COUNT(*)                                        AS order_cnt,
                SUM(po_tot_price)                               AS order_amt
              FROM O
            ),
            OI_AGG AS (
              /* 배송 관련: '주문 수' 기준이면 DISTINCT, '항목 수'면 DISTINCT 제거 */
              SELECT
                COUNT(DISTINCT CASE WHEN oi_del_status IN ('배송준비중','배송중','배송완료')
                                    THEN oi_po_no END) AS shipping_cnt
              FROM OI
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
              NVL(o.confirm_done, 0)  AS confirm_done,
              NVL(o.canceled_cnt, 0)  AS canceled_cnt,
              NVL(o.exchange_cnt, 0)  AS exchange_cnt,
              NVL(o.return_cnt,   0)  AS return_cnt,
              NVL(oi.shipping_cnt, 0) AS shipping_cnt,
              NVL(o.order_cnt,    0)  AS order_cnt,
              NVL(o.order_amt,    0)  AS order_amt,   -- ★ 여기 oj 아님!
              uu.signup_cnt            AS signup_cnt,
              0                        AS visit_cnt,
              bd.board_cnt             AS board_cnt
            FROM O_AGG o
            CROSS JOIN OI_AGG oi
            CROSS JOIN UU uu
            CROSS JOIN BD bd            
            """;

        Query q = em.createNativeQuery(sql);
        q.setParameter("fromDate", Timestamp.valueOf(from.atStartOfDay()));
        q.setParameter("toDate",   Timestamp.valueOf(to.plusDays(1).atStartOfDay().minusSeconds(1)));
        return (Object[]) q.getSingleResult();
    }
}