package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleMainMapper {

//    활성화된 체험 공고 수
    int countActiveExperienceNotice(@Param("companyId") Long companyId);

//    전체 체험 공고 수
    int countAllExperienceNotice(@Param("companyId") Long companyId);

    int countActiveInternNotice(@Param("companyId") Long companyId);

    int countAllInternNotice(@Param("companyId") Long companyId);

//    지원한 회원 목록
    List<ConsoleExperienceMemberDTO> selectRecentActiveExperienceMembers(@Param("companyId") Long companyId);

}
