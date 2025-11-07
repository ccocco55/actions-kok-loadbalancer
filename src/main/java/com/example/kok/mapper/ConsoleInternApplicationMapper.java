package com.example.kok.mapper;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.enumeration.RequestStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsoleInternApplicationMapper {

//    지원자 상세 조회
    ConsoleInternApplicantDTO selectApplicationDetail(
            @Param("memberId") Long memberId,
            @Param("internNoticeId") Long internNoticeId
    );

    Optional<FileDTO> selectResumeFileByMemberId(
            @Param("memberId") Long memberId,
            @Param("internNoticeId") Long internNoticeId);

//    공고별 지원자 목록
    List<ConsoleInternApplicantDTO> selectApplicantsByNoticeId(@Param("internNoticeId") Long internNoticeId);

//    지원자 상태 변경
    public void updateApplicantStatus(@Param("userId") Long userId,
                                      @Param("internNoticeId") Long internNoticeId,
                                      @Param("requestInternStatus") RequestStatus requestInternStatus);
}
