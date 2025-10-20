package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminCouponMapper {


    List<CouponDTO> selectCouponList(@Param("req") PageRequestDTO req);
    int countCouponList(@Param("req") PageRequestDTO req);

    int insertCoupon(CouponDTO dto);


    int closeCoupon(String cId);

    CouponDTO selectCouponDetail(String cId);

}
