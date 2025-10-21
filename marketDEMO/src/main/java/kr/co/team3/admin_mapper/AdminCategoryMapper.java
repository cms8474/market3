package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.AdminCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminCategoryMapper {
    List<AdminCategoryDTO> selectLevel1(); // 대
    List<AdminCategoryDTO> selectLevel2(@Param("parentId") String parentId); // 중
}
