package kr.co.team3.product_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.product_dto.MemberDTO;
import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_entity.SellerInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    
    void insertMember(MemberEntity member);
    
    int countById(String uId);
    
    int countByMail(String uMail);
    
    MemberEntity findByuIdAnduPw(Map<String, String> params);
    
    MemberEntity findByuMail(String uMail);
    
    MemberEntity findByuId(String uId);
    
    // 회원 정보 업데이트
    void updateMember(MemberEntity member);
    
    // SELLER_INFO 테이블 관련
    void insertSellerInfo(SellerInfoEntity sellerInfo);
    
    // 약관 조회
    String getTermsContent(String termName);


    // ========== 관리자 기능 ==========
    //관리자 회원목록 조회
    List<MemberDTO> selectMemberList(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    int selectCountTotal(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);


    // 회원 단건 조회 , 수정
    MemberDTO selectMemberById(String u_id);

    int updateMemberProfile(MemberDTO dto);


    //회원 상태 업데이트
    int updateStatusToStop(String u_id);

    int updateStatusToActive(String u_id);

    int deactivateMember(String u_id);

    int makeDormantOver90Days();

    //선택수정
    int bulkUpdateRanks(@Param("list") List<MemberDTO> list);


}
