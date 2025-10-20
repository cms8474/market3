package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner,Long>{

    @Query("select b from Banner b where b.aLocation = :loc order by b.aNo desc")
    List<Banner> findByALocationOrderByANoDesc(@Param("loc") String loc);

    @Query("select b from Banner b order by b.aNo desc")
    List<Banner> findAllByOrderByANoDesc();


}

