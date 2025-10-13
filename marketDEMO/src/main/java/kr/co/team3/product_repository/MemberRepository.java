package kr.co.team3.product_repository;

import kr.co.team3.product_entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    // ✅ 로그인용 (정확히 엔티티 필드명 기준)
    @Query("SELECT m FROM MemberEntity m WHERE m.uId = :uId AND m.uPw = :uPw")
    MemberEntity findByuIdAnduPw(@Param("uId") String uId, @Param("uPw") String uPw);

    // ✅ 이메일 중복검사용 (정확히 필드명 uMail)
    @Query("SELECT m FROM MemberEntity m WHERE m.uMail = :uMail")
    MemberEntity findByuMail(@Param("uMail") String uMail);
}
