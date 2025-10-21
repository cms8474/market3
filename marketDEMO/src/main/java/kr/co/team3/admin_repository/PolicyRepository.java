package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Terms, String> {
    @Query("select t from Terms t where t.tName = :name")
    Optional<Terms> findByName(@Param("name") String name);
}
