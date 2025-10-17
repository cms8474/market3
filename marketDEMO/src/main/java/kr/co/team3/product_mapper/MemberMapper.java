package kr.co.team3.product_mapper;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_entity.SellerInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface MemberMapper {
    
    void insertMember(MemberEntity member);
    
    int countById(String uId);
    
    int countByMail(String uMail);
    
    MemberEntity findByuIdAnduPw(Map<String, String> params);
    
    MemberEntity findByuMail(String uMail);
    
    MemberEntity findByuId(String uId);
    
    // SELLER_INFO 테이블 관련
    void insertSellerInfo(SellerInfoEntity sellerInfo);
    
    // 약관 조회
    String getTermsContent(String termName);
}
