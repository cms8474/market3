package kr.co.team3.company_mapper;

import kr.co.team3.company_entity.RecruitEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecruitMapper {
    List<RecruitEntity> findAll();
    List<RecruitEntity> findPaged(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();

}
