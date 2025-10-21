package kr.co.team3.repository;

import kr.co.team3.entity.OrderItems;
import kr.co.team3.entity.OrderItemsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemsId> {
}
