package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleExperienceDetailMapper {

//    공고 상세 내용
    ConsoleExperienceNoticeDTO selectExperienceDatailByCompany(@Param("experienceNoticeId") Long experienceNoticeId);

//    지원 목록
    public List<ConsoleExperienceApplicantDTO> selectExperienceApplicantByCompany(@Param("experienceNoticeId") Long experienceNoticeId,
                                                                                  @Param("status") RequestStatus status,
                                                                                  @Param("criteria") Criteria criteria);

//    지원 목록 개수
    public int selectExperienceApplicantCountByCompany(@Param("experienceNoticeId") Long experienceNoticeId,
                                                       @Param("status") RequestStatus status);

}
