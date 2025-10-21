package kr.co.team3.product_dto;

import lombok.*;

import java.time.LocalDateTime;
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
    private String pDesc;
    private Integer pPrice;
    private Integer pDiscount;
    private Integer pPoint;
    private Integer pStockQuantity;
    private Integer pDeliveryPrice;
    private LocalDateTime pRegdate;
    private String pSellerId;
    private String pPcId;
    private String pImageList;
    private String pImageMain;
    private String pImageDetail;
    private String pDetailInfo;
    private Integer pViewCount;
    private String imageBase64;
    private Double starAvg;
    private String sCompanyName;

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

    // ✅ 3️⃣ 상품 목록용 (기본 정보)
    public IndexDTO(String pPid, String pName, Integer pPrice, Integer pDiscount, Integer pViewCount, String pImageMain) {
        this.pPid = pPid;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pViewCount = pViewCount;
        this.pImageMain = pImageMain;
        this.imageBase64 = pImageMain; // 이미지 경로를 그대로 사용
    }

    // ✅ 3-1️⃣ 상품 목록용 (배송비 포함)
    public IndexDTO(String pPid, String pName, Integer pPrice, Integer pDiscount, Integer pViewCount, String pImageMain, Integer pDeliveryPrice) {
        this.pPid = pPid;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pViewCount = pViewCount;
        this.pImageMain = pImageMain;
        this.pDeliveryPrice = pDeliveryPrice;
        this.imageBase64 = pImageMain; // 이미지 경로를 그대로 사용
    }

    // 4️⃣ 상품 상세용 (모든 정보)
    public IndexDTO(String pPid, String pName, String pDesc, Integer pPrice, Integer pDiscount, 
                   Integer pPoint, Integer pStockQuantity, Integer pDeliveryPrice, 
                   LocalDateTime pRegdate, String pSellerId, String pPcId, 
                   String pImageList, String pImageMain, String pImageDetail, 
                   String pDetailInfo, Integer pViewCount) {
        this.pPid = pPid;
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pPoint = pPoint;
        this.pStockQuantity = pStockQuantity;
        this.pDeliveryPrice = pDeliveryPrice;
        this.pRegdate = pRegdate;
        this.pSellerId = pSellerId;
        this.pPcId = pPcId;
        this.pImageList = pImageList;
        this.pImageMain = pImageMain;
        this.pImageDetail = pImageDetail;
        this.pDetailInfo = pDetailInfo;
        this.pViewCount = pViewCount;
        this.imageBase64 = pImageMain; // 이미지 경로를 그대로 사용
    }

    // 5️⃣ 회사 정보용
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
