package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.RequestDownloadUrlDTO;
import com.example.kok.enumeration.RequestStatus;

import java.util.List;

public interface ConsoleExperienceApplicationService {
    //    지원자 상세 내용
    ConsoleExperienceApplicantDTO getApplicationsDetail(Long memberId, Long experienceNoticeId);

    //    지원자 목록 (공고 ID로 조회)
    List<ConsoleExperienceApplicantDTO> getApplicationsByNoticeId(Long experienceNoticeId);

    //    여러 명의 지원자 상세 조회
    List<ConsoleExperienceApplicantDTO> getApplicationsDetailsByMemberIds(Long experienceNoticeId, List<Long> memberIdList);

    //    전체 지원자 파일 다운로드 URL 생성
    RequestDownloadUrlDTO getApplicationFileInfo(List<Long> memberIdList, Long experienceNoticeId);

    //    평가하기 가능 여부 조회
    public boolean isEvalOk(Long experienceNoticeId, Long memberId, Long requestExperienceId);

    //    지원자 상태 변경
    public void updateApplicantStatus(Long userId, Long experienceNoticeId, RequestStatus requestStatus);
}
