package kr.co.team3.admin_repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Repository
@Transactional(readOnly = true)
public class DashboardRepository {

    @PersistenceContext
    private EntityManager em;

    // 운영현황 카운트
    public Map<String, Object> findOpsCounts() {
        String sql = """
            SELECT
              SUM(CASE WHEN STATUS='WAITING_PAYMENT' THEN 1 ELSE 0 END) AS WAITING,
              SUM(CASE WHEN STATUS='READY_SHIPMENT'  THEN 1 ELSE 0 END) AS READY,
              SUM(CASE WHEN STATUS='CANCEL_REQ'      THEN 1 ELSE 0 END) AS CANCEL_REQ,
              SUM(CASE WHEN STATUS='EXCHANGE_REQ'    THEN 1 ELSE 0 END) AS EXCHANGE_REQ,
              SUM(CASE WHEN STATUS='RETURN_REQ'      THEN 1 ELSE 0 END) AS RETURN_REQ
            FROM ORDERS
        """;
        Object[] row = (Object[]) em.createNativeQuery(sql).getSingleResult();
        Map<String, Object> m = new HashMap<>();
        m.put("WAITING",      toLong(row[0]));
        m.put("READY",        toLong(row[1]));
        m.put("CANCEL_REQ",   toLong(row[2]));
        m.put("EXCHANGE_REQ", toLong(row[3]));
        m.put("RETURN_REQ",   toLong(row[4]));
        return m;
    }

    // 총계
    public Map<String, Object> findTotals() {
        String sql = """
            SELECT
              (SELECT COUNT(*)           FROM ORDERS)                               AS ORDER_COUNT,
              (SELECT NVL(SUM(AMOUNT),0) FROM ORDERS WHERE STATUS='PAID')           AS ORDER_AMOUNT,
              (SELECT COUNT(*)           FROM MEMBER)                               AS REGISTER_COUNT,
              (SELECT COUNT(*)           FROM VISIT_LOG)                            AS VISITOR,
              (SELECT COUNT(*)           FROM QNA WHERE STATUS='Y')                 AS QNA
            FROM DUAL
        """;
        Object[] row = (Object[]) em.createNativeQuery(sql).getSingleResult();
        Map<String, Object> m = new HashMap<>();
        m.put("ORDER_COUNT",   toLong(row[0]));
        m.put("ORDER_AMOUNT",  toLong(row[1]));
        m.put("REGISTER_COUNT",toLong(row[2]));
        m.put("VISITOR",       toLong(row[3]));
        m.put("QNA",           toLong(row[4]));
        return m;
    }

    // 특정 범위 합계(from <= ts < to)
    public Map<String, Object> findRangeTotals(java.time.LocalDateTime from, java.time.LocalDateTime to) {
        String sql = """
            SELECT
              (SELECT COUNT(*)           FROM ORDERS    WHERE CREATED_AT >= :from AND CREATED_AT < :to)                      AS ORDER_COUNT,
              (SELECT NVL(SUM(AMOUNT),0) FROM ORDERS    WHERE STATUS='PAID' AND CREATED_AT >= :from AND CREATED_AT < :to)    AS ORDER_AMOUNT,
              (SELECT COUNT(*)           FROM MEMBER    WHERE CREATED_AT >= :from AND CREATED_AT < :to)                      AS REGISTER_COUNT,
              (SELECT COUNT(*)           FROM VISIT_LOG WHERE VISITED_AT >= :from AND VISITED_AT < :to)                      AS VISITOR,
              (SELECT COUNT(*)           FROM QNA       WHERE STATUS='Y' AND CREATED_AT >= :from AND CREATED_AT < :to)       AS QNA
            FROM DUAL
        """;
        Query q = em.createNativeQuery(sql)
                .setParameter("from", from)
                .setParameter("to", to);
        Object[] row = (Object[]) q.getSingleResult();
        Map<String, Object> m = new HashMap<>();
        m.put("ORDER_COUNT",   toLong(row[0]));
        m.put("ORDER_AMOUNT",  toLong(row[1]));
        m.put("REGISTER_COUNT",toLong(row[2]));
        m.put("VISITOR",       toLong(row[3]));
        m.put("QNA",           toLong(row[4]));
        return m;
    }

    // Bar 차트: 최근 5일
    public List<Object[]> findBar5Days() {
        String sql = """
            WITH days AS (
              SELECT TRUNC(SYSDATE) - LEVEL + 1 AS d
              FROM DUAL CONNECT BY LEVEL <= 5
            )
            SELECT
              TO_CHAR(d.d, 'MM-DD') AS LABEL,
              (SELECT COUNT(*)           FROM ORDERS o WHERE TRUNC(o.CREATED_AT)=d.d)                           AS ORDERS,
              (SELECT NVL(SUM(AMOUNT),0) FROM ORDERS o WHERE STATUS='PAID' AND TRUNC(o.CREATED_AT)=d.d)         AS PAYMENTS,
              (SELECT COUNT(*)           FROM ORDERS o WHERE STATUS='CANCELLED' AND TRUNC(o.CREATED_AT)=d.d)    AS CANCELS
            FROM days d
            ORDER BY d.d
        """;
        @SuppressWarnings("unchecked")
        List<Object[]> list = em.createNativeQuery(sql).getResultList();
        return list;
    }

    // Pie 차트: 최근 30일 카테고리 합계
    public List<Object[]> findPieByCategory() {
        String sql = """
            SELECT CATEGORY, SUM(QTY) AS TOTAL_QTY
            FROM ORDER_ITEM oi
            JOIN ORDERS o ON o.ID = oi.ORDER_ID
            WHERE o.STATUS='PAID'
              AND o.CREATED_AT >= SYSTIMESTAMP - 30
            GROUP BY CATEGORY
            ORDER BY TOTAL_QTY DESC
        """;
        @SuppressWarnings("unchecked")
        List<Object[]> list = em.createNativeQuery(sql).getResultList();
        return list;
    }

    private long toLong(Object v) {
        if (v == null) return 0L;
        if (v instanceof Number n) return n.longValue();
        if (v instanceof BigDecimal bd) return bd.longValue();
        return Long.parseLong(v.toString());
    }
}
