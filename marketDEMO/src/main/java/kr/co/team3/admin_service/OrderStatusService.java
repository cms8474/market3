package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.OrderStatusDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.OrderStatusMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderStatusMapper orderStatusMapper;

    /**
     * 주문현황 목록 조회
     * - 관리자: 전체 주문
     * - 판매자: 자신의 상품이 포함된 주문만
     */
    public PageResponseDTO<OrderStatusDTO> selectAll(PageRequestDTO req) {
        log.info("▶ [OrderStatus] selectAll() called. pg={}, size={}, searchType={}, keyword={}, userType={}, sellerId={}",
                req.getPg(), req.getSize(), req.getSearchType(), req.getKeyword(), req.getUserType(), req.getSellerId());

        // 목록 조회
        List<OrderStatusDTO> list = orderStatusMapper.selectOrderStatusPage(req);

        if (list == null) {
            log.warn("⚠️ [OrderStatus] selectOrderStatusPage returned NULL → empty list");
            list = Collections.emptyList();
        } else {
            long nullCount = list.stream().filter(Objects::isNull).count();
            if (nullCount > 0) {
                list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
                log.info("🧹 [OrderStatus] removed {} null elements. sizeAfterFilter={}", nullCount, list.size());
            }
        }

        // 총 개수
        int total = orderStatusMapper.countOrderStatus(req);
        log.info("📊 [OrderStatus] total count={}", total);

        return new PageResponseDTO<>(req, list, total);
    }

    /**
     * 배송상태 자동 갱신 (매일 새벽 3시)
     * 1. '배송중' → 3일 경과 시 '배송완료'
     * 2. '상품준비중' → 1일 경과 시 '배송중'
     */
    @Transactional
    public int runAutoUpdate() {
        log.info("⚙️ [배송상태 자동갱신] 시작");
        int updatedDelivered = orderStatusMapper.updateToDelivered();
        int updatedShipping = orderStatusMapper.updateToShipping();
        int totalUpdated = updatedDelivered + updatedShipping;
        log.info("[배송상태 자동갱신 완료] 배송중→배송완료:{}건, 상품준비중→배송중:{}건, 총합:{}건",
                updatedDelivered, updatedShipping, totalUpdated);
        return totalUpdated;
    }
}
