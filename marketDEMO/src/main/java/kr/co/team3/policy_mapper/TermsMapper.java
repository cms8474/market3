package kr.co.team3.policy_mapper;

import kr.co.team3.policy_dto.TermsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

@Mapper
@Repository
public interface TermsMapper {
    TermsDTO findByName(@Param("tName") String tName);
}
