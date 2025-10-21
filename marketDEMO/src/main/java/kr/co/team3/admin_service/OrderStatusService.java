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
    public PageResponseDTO<OrderStatusDTO> selectAll(PageRequestDTO req) {
        log.info(">>> [OrderStatus] selectAll() called. pg={}, size={}, searchType={}, keyword={}",
                req.getPg(), req.getSize(), req.getSearchType(), req.getKeyword());

        List<OrderStatusDTO> list = orderStatusMapper.selectOrderStatusList(req);

        if (list == null) {
            log.info(">>> [OrderStatus] selectOrderStatusList returned NULL; using empty list");
            list = Collections.emptyList();
        } else {
            long nullCount = list.stream().filter(Objects::isNull).count();
            log.info(">>> [OrderStatus] fetched size={}, nullElements={}", list.size(), nullCount);

            if (nullCount > 0) {
                list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
                log.info(">>> [OrderStatus] sanitized sizeAfterFilter={}", list.size());
            }
        }

        int total = orderStatusMapper.countOrderStatus(req);
        log.info(">>> [OrderStatus] total count={}", total);

        return new PageResponseDTO<>(req, list, total);
    }

    @Transactional
    public int runAutoUpdate() {
        // 순서 중요: 3일→배송완료 먼저, 그다음 1일→배송중
        int updatedDelivered = orderStatusMapper.updateToDelivered();
        int updatedShipping = orderStatusMapper.updateToShipping();
        return updatedDelivered + updatedShipping;
    }
}