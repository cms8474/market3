package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.product_dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminShopMapper {
    // 판매자(상점) 목록 조회
    List<MemberDTO> selectShopList(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    // 전체 개수 조회 (페이징용)
    int selectShopTotal(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);


    // 등록 (유저, 상점)
    int insertUserForSeller(MemberDTO dto);
    int insertSellerInfo(MemberDTO dto);

    // 중복 체크
    int existsUserId(@Param("u_id") String u_id);
    int existsSellerNo(@Param("s_seller_no") String s_seller_no);
    int existsSalesRegNum(@Param("s_sales_reg_num") String s_sales_reg_num);

    //삭제
    int deleteSellerInfos(@Param("ids") List<String> ids);
    int deleteUsers(@Param("ids") List<String> ids);

    //업데이트
    int approveSeller(@Param("u_id") String uId);
    int stopSeller(@Param("u_id") String uId);
    int resumeSeller(@Param("u_id") String uId);

}