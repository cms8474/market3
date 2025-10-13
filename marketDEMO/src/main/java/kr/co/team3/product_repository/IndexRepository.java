package kr.co.team3.product_repository;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * 메인 페이지 데이터 통합 Repository
 * - 배너 / 카테고리 / 상품 / 회사정보 조회
 */
public interface IndexRepository extends JpaRepository<IndexEntity, String> {

    /* ===============================
       ✅ 배너 조회 (TOP / SLIDE)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            a.aLink,
            a.aLocation,
            a.aFile
        )
        FROM AdEntity a
        WHERE a.aLocation = :location
        ORDER BY a.aId ASC
    """)
    List<IndexDTO> findByAdLocation(String location);


    /* ===============================
       ✅ 카테고리 목록 조회
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            c.pcId,
            c.pcName,
            c.pcLever
        )
        FROM CategoryEntity c
        ORDER BY c.pcId ASC
    """)
    List<IndexDTO> findCategories();


    /* ===============================
       ✅ 베스트 상품 (조회수 상위)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            i.pPid,
            i.pName,
            i.pPrice,
            i.pDiscount,
            i.pViewCount,
            i.pImageMain
        )
        FROM IndexEntity i
        ORDER BY i.pViewCount DESC
    """)
    List<IndexDTO> findBestProducts(Pageable pageable);


    /* ===============================
       ✅ 히트 상품 (조회수 순)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            i.pPid,
            i.pName,
            i.pPrice,
            i.pDiscount,
            i.pViewCount,
            i.pImageMain
        )
        FROM IndexEntity i
        ORDER BY i.pViewCount DESC
    """)
    List<IndexDTO> findHitProducts(Pageable pageable);


    /* ===============================
       ✅ 추천 상품 (TAG_LINKS 연결)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            i.pPid,
            i.pName,
            i.pPrice,
            i.pDiscount,
            i.pViewCount,
            i.pImageMain
        )
        FROM IndexEntity i
        JOIN TagLinkEntity t ON i.pPid = t.tlPPid
        WHERE t.tlPtName = '추천'
        ORDER BY i.pRegdate DESC
    """)
    List<IndexDTO> findRecommendProducts(Pageable pageable);


    /* ===============================
       ✅ 최신 상품 (등록일 최신순)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            i.pPid,
            i.pName,
            i.pPrice,
            i.pDiscount,
            i.pViewCount,
            i.pImageMain
        )
        FROM IndexEntity i
        ORDER BY i.pRegdate DESC
    """)
    List<IndexDTO> findLatestProducts(Pageable pageable);


    /* ===============================
       ✅ 할인 상품 (할인율 높은 순)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            i.pPid,
            i.pName,
            i.pPrice,
            i.pDiscount,
            i.pViewCount,
            i.pImageMain
        )
        FROM IndexEntity i
        WHERE i.pDiscount > 0
        ORDER BY i.pDiscount DESC
    """)
    List<IndexDTO> findDiscountProducts(Pageable pageable);


    /* ===============================
       ✅ 회사 정보 (Footer용)
       =============================== */
    @Query("""
        SELECT new kr.co.team3.product_dto.IndexDTO(
            v.vCompany,
            v.vEmail,
            v.vAddr,
            v.vTel
        )
        FROM VersionEntity v
        ORDER BY v.vId ASC
    """)
    List<IndexDTO> findCompanyInfo();
}
