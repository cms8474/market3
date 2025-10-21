package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    Page<Recruitment> findByRecruitNo(Integer recruitNo, Pageable pageable);
    Page<Recruitment> findByRecruitDeptContainingIgnoreCase(String recruitDept, Pageable pageable);
    Page<Recruitment> findByRecruitTypeContainingIgnoreCase(String recruitType, Pageable pageable);
    Page<Recruitment> findByRecruitTitleContainingIgnoreCase(String recruitTitle, Pageable pageable);
}
