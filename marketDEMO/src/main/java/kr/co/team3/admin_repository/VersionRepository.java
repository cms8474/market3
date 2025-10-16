package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version,Long> {
    Optional<Version> findTopByOrderByIdDesc();

}
