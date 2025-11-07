package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.enumeration.RequestStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsoleExperienceApplicationMapper {

//    지원자 상세 조회
    ConsoleExperienceApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("experienceNoticeId") Long experienceNoticeId
    );

    Optional<FileDTO> selectResumeFileByMemberId(
            @Param("memberId") Long memberId,
            @Param("experienceNoticeId") Long experienceNoticeId);

//    공고별 지원자 목록
    List<ConsoleExperienceApplicantDTO> selectApplicantsByNoticeId(@Param("experienceNoticeId") Long experienceNoticeId);

//    평가하기 가능 여부
    public RequestExperienceDTO selectEvalOk(Long experienceNoticeId, Long memberId);

//    지원자 상태 변경
    public void updateApplicantStatus(@Param("userId") Long userId,
                                      @Param("experienceNoticeId") Long experienceNoticeId,
                                      @Param("requestExperienceStatus") RequestStatus requestExperienceStatus);

}
