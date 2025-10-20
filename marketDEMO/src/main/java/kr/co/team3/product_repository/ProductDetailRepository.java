package kr.co.team3.product_repository;

import kr.co.team3.product_entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, String> {
    Optional<ProductDetailEntity> findByPdPid(String pdPid);
}
