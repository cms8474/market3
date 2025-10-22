package kr.co.team3.admin_mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    String selectUserType(@Param("uid") String uid);
}
