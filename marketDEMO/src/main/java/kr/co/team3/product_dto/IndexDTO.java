package kr.co.team3.product_dto;

import lombok.*;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndexDTO {

    // ===== 상품 정보 =====
    private String pPid;
    private String pName;
    private Integer pPrice;
    private Integer pDiscount;
    private Integer pViewCount;
    private String imageBase64;

    // ===== 카테고리 =====
    private String pcId;
    private String pcName;
    private String pcLever;

    // ===== 배너 =====
    private String aLink;
    private String aLocation;
    private String aImageBase64;

    // ===== 회사 정보 =====
    private String vCompany;
    private String vEmail;
    private String vAddr;
    private String vTel;

    /* ========================
       ✅ JPQL 생성자 정의
       ======================== */

    // 1️⃣ 배너용 (BLOB → Base64 변환)
    public IndexDTO(String aLink, String aLocation, byte[] aFile) {
        this.aLink = aLink;
        this.aLocation = aLocation;
        this.aImageBase64 = convertToBase64(aFile);
    }

    // 2️⃣ 카테고리용
    public IndexDTO(String pcId, String pcName, String pcLever) {
        this.pcId = pcId;
        this.pcName = pcName;
        this.pcLever = pcLever;
    }

    // ✅ 3️⃣ Hibernate JPQL용 — convertToBase64() 반환값(String)을 받는 생성자
    public IndexDTO(String pPid, String pName, Integer pPrice, Integer pDiscount, Integer pViewCount, String imageBase64) {
        this.pPid = pPid;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pViewCount = pViewCount;
        this.imageBase64 = imageBase64;
    }

    // 4️⃣ 기존 byte[] 기반 (조회수 포함) — 다른 쿼리에서 사용 가능
    public IndexDTO(String pPid, String pName, Integer pPrice, Integer pDiscount, Integer pViewCount, byte[] pImageMain) {
        this.pPid = pPid;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pViewCount = pViewCount;
        this.imageBase64 = convertToBase64(pImageMain);
    }

    // 5️⃣ 상품용 (조회수 없이)
    public IndexDTO(String pPid, String pName, Integer pPrice, Integer pDiscount, byte[] pImageMain) {
        this.pPid = pPid;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.imageBase64 = convertToBase64(pImageMain);
    }

    // 6️⃣ 회사 정보용
    public IndexDTO(String vCompany, String vEmail, String vAddr, String vTel) {
        this.vCompany = vCompany;
        this.vEmail = vEmail;
        this.vAddr = vAddr;
        this.vTel = vTel;
    }

    // ✅ BLOB → Base64 변환
    public static String convertToBase64(byte[] blob) {
        return (blob != null)
                ? "data:image/png;base64," + Base64.getEncoder().encodeToString(blob)
                : null;
    }
}
