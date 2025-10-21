package kr.co.team3.service;

import kr.co.team3.entity.*;
import kr.co.team3.repository.*;
import kr.co.team3.product_service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductOrderRepository productOrderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final UserCouponRepository userCouponRepository;
    private final MemberService memberService;
    private final EntityManager entityManager;

    @Transactional
    public String processOrder(OrderRequest orderRequest) {
        log.info("주문 처리 시작: {}", orderRequest);

        // 1. PRODUCT_ORDER 테이블에 주문 정보 저장 (PO_NO는 NULL로 저장하여 DB 트리거가 자동 생성하도록 함)
        ProductOrder productOrder = ProductOrder.builder()
                .poNo(null) // DB 트리거로 자동 생성
                .poUId(orderRequest.getUserId())
                .poPayType(orderRequest.getPaymentMethod())
                .poPayDetail(null)
                .poRecipient(orderRequest.getRecipient())
                .poRePhone(orderRequest.getPhone())
                .poItemCount(orderRequest.getItemCount())
                .poSumPrice(orderRequest.getProductAmount())
                .poDiscount(orderRequest.getDiscountAmount())
                .poDeliveryPrice(orderRequest.getDeliveryFee())
                .poPriDiscount(orderRequest.getAdditionalDiscount())
                .poTotPrice(orderRequest.getFinalAmount())
                .poGetPoint(orderRequest.getEarnedPoint())
                .poState("결제완료")
                .poPostal(orderRequest.getPostal())
                .poBaseAddr(orderRequest.getBaseAddr())
                .poDetailAddr(orderRequest.getDetailAddr())
                .poRequestNote(orderRequest.getRequest())
                .poOrderdate(LocalDateTime.now())
                .build();

        // DB 트리거를 사용하기 위해 Native Query로 직접 INSERT
        String insertSql = """
            INSERT INTO product_order (
                po_u_id, po_pay_type, po_pay_detail, po_recipient, po_re_phone,
                po_item_count, po_sum_price, po_discount, po_delivery_price,
                po_pri_discount, po_tot_price, po_get_point, po_state,
                po_postal, po_base_addr, po_detail_addr, po_request_note, po_orderdate
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        
        entityManager.createNativeQuery(insertSql)
                .setParameter(1, productOrder.getPoUId())
                .setParameter(2, productOrder.getPoPayType())
                .setParameter(3, productOrder.getPoPayDetail())
                .setParameter(4, productOrder.getPoRecipient())
                .setParameter(5, productOrder.getPoRePhone())
                .setParameter(6, productOrder.getPoItemCount())
                .setParameter(7, productOrder.getPoSumPrice())
                .setParameter(8, productOrder.getPoDiscount())
                .setParameter(9, productOrder.getPoDeliveryPrice())
                .setParameter(10, productOrder.getPoPriDiscount())
                .setParameter(11, productOrder.getPoTotPrice())
                .setParameter(12, productOrder.getPoGetPoint())
                .setParameter(13, productOrder.getPoState())
                .setParameter(14, productOrder.getPoPostal())
                .setParameter(15, productOrder.getPoBaseAddr())
                .setParameter(16, productOrder.getPoDetailAddr())
                .setParameter(17, productOrder.getPoRequestNote())
                .setParameter(18, productOrder.getPoOrderdate())
                .executeUpdate();
        
        // 생성된 주문번호를 가져오기 위해 마지막으로 삽입된 레코드 조회
        String selectSql = "SELECT po_no FROM product_order WHERE po_u_id = ? ORDER BY po_orderdate DESC";
        String orderNumber = (String) entityManager.createNativeQuery(selectSql)
                .setParameter(1, productOrder.getPoUId())
                .setMaxResults(1)
                .getSingleResult();
        
        log.info("PRODUCT_ORDER 저장 완료, DB 트리거로 생성된 주문번호: {}", orderNumber);

        // 3. ORDER_ITEMS 테이블에 주문 상품 정보 저장
        for (OrderItemRequest item : orderRequest.getOrderItems()) {
            OrderItems orderItem = OrderItems.builder()
                    .oiPoNo(orderNumber)
                    .oiPPid(item.getProductId())
                    .oiSUId(item.getSellerId())
                    .oiProdQty(item.getQuantity())
                    .oiTotPrice(item.getTotalPrice())
                    .oiTrackingCompany(null)
                    .oiTrackingNum(null)
                    .oiEtc(null)
                    .oiDelStatus("배송준비")
                    .build();

            orderItemsRepository.save(orderItem);
            log.info("ORDER_ITEMS 저장 완료: {} - {}", orderNumber, item.getProductId());
        }

        // 4. 포인트 사용 처리
        log.info("포인트 사용 확인: usedPoint={}", orderRequest.getUsedPoint());
        if (orderRequest.getUsedPoint() > 0) {
            log.info("포인트 사용 처리 시작: 사용자={}, 사용포인트={}", orderRequest.getUserId(), orderRequest.getUsedPoint());
            processPointUsage(orderNumber, orderRequest.getUserId(), orderRequest.getUsedPoint());
        } else {
            log.info("포인트 사용하지 않음");
        }

        // 5. 쿠폰 사용 처리
        if (orderRequest.getUsedCouponId() != null && !orderRequest.getUsedCouponId().isEmpty()) {
            processCouponUsage(orderNumber, orderRequest.getUserId(), orderRequest.getUsedCouponId());
        }

        log.info("주문 처리 완료: {}", orderNumber);
        return orderNumber;
    }

    private void processPointUsage(String orderNumber, String userId, int usedPoint) {
        log.info("포인트 사용 처리 시작: 사용자={}, 사용포인트={}, 주문번호={}", userId, usedPoint, orderNumber);

        try {
            // U_USER 테이블의 포인트 차감
            log.info("회원 포인트 차감 시작");
            memberService.deductPoint(userId, usedPoint);
            log.info("회원 포인트 차감 완료");

            // POINT_HISTORY 테이블에 기록
            String pointHistoryId = "PH_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            log.info("포인트 히스토리 ID 생성: {}", pointHistoryId);
            
            PointHistory pointHistory = PointHistory.builder()
                    .uhNo(pointHistoryId)
                    .uhUId(userId)
                    .uhPoNo(orderNumber)
                    .uhText("상품 구매 포인트 사용")
                    .uhChangePoint(-usedPoint) // 음수로 저장
                    .uhChangeDate(null)
                    .uhDday(LocalDateTime.now())
                    .build();

            log.info("포인트 히스토리 저장 시작: {}", pointHistory);
            pointHistoryRepository.save(pointHistory);
            log.info("포인트 사용 기록 저장 완료: {}", pointHistoryId);
        } catch (Exception e) {
            log.error("포인트 사용 처리 중 오류 발생", e);
            throw e;
        }
    }

    private void processCouponUsage(String orderNumber, String userId, String couponId) {
        log.info("쿠폰 사용 처리: 사용자={}, 쿠폰={}", userId, couponId);

        // USER_COUPON 테이블에 사용 기록
        UserCouponId userCouponId = new UserCouponId(userId, couponId);
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId).orElse(null);
        if (userCoupon != null) {
            userCoupon.setUcPoNo(orderNumber);
            userCoupon.setUcStatus("사용완료");
            userCoupon.setUcUseDay(LocalDateTime.now());
            userCouponRepository.save(userCoupon);
            log.info("USER_COUPON 테이블 업데이트 완료: {} - {}", userId, couponId);
        } else {
            log.warn("USER_COUPON을 찾을 수 없습니다: {} - {}", userId, couponId);
        }
    }


    // 주문 요청 DTO
    public static class OrderRequest {
        private String userId;
        private String paymentMethod;
        private String recipient;
        private String phone;
        private int itemCount;
        private int productAmount;
        private int discountAmount;
        private int deliveryFee;
        private int additionalDiscount;
        private int finalAmount;
        private int earnedPoint;
        private int usedPoint;
        private String usedCouponId;
        private String postal;
        private String baseAddr;
        private String detailAddr;
        private String request;
        private List<OrderItemRequest> orderItems;

        // Getters and Setters
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getRecipient() { return recipient; }
        public void setRecipient(String recipient) { this.recipient = recipient; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public int getItemCount() { return itemCount; }
        public void setItemCount(int itemCount) { this.itemCount = itemCount; }
        public int getProductAmount() { return productAmount; }
        public void setProductAmount(int productAmount) { this.productAmount = productAmount; }
        public int getDiscountAmount() { return discountAmount; }
        public void setDiscountAmount(int discountAmount) { this.discountAmount = discountAmount; }
        public int getDeliveryFee() { return deliveryFee; }
        public void setDeliveryFee(int deliveryFee) { this.deliveryFee = deliveryFee; }
        public int getAdditionalDiscount() { return additionalDiscount; }
        public void setAdditionalDiscount(int additionalDiscount) { this.additionalDiscount = additionalDiscount; }
        public int getFinalAmount() { return finalAmount; }
        public void setFinalAmount(int finalAmount) { this.finalAmount = finalAmount; }
        public int getEarnedPoint() { return earnedPoint; }
        public void setEarnedPoint(int earnedPoint) { this.earnedPoint = earnedPoint; }
        public int getUsedPoint() { return usedPoint; }
        public void setUsedPoint(int usedPoint) { this.usedPoint = usedPoint; }
        public String getUsedCouponId() { return usedCouponId; }
        public void setUsedCouponId(String usedCouponId) { this.usedCouponId = usedCouponId; }
        public String getPostal() { return postal; }
        public void setPostal(String postal) { this.postal = postal; }
        public String getBaseAddr() { return baseAddr; }
        public void setBaseAddr(String baseAddr) { this.baseAddr = baseAddr; }
        public String getDetailAddr() { return detailAddr; }
        public void setDetailAddr(String detailAddr) { this.detailAddr = detailAddr; }
        public String getRequest() { return request; }
        public void setRequest(String request) { this.request = request; }
        public List<OrderItemRequest> getOrderItems() { return orderItems; }
        public void setOrderItems(List<OrderItemRequest> orderItems) { this.orderItems = orderItems; }
    }

    // 주문 상품 DTO
    public static class OrderItemRequest {
        private String productId;
        private String sellerId;
        private int quantity;
        private int totalPrice;

        // Getters and Setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getSellerId() { return sellerId; }
        public void setSellerId(String sellerId) { this.sellerId = sellerId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public int getTotalPrice() { return totalPrice; }
        public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    }
}
