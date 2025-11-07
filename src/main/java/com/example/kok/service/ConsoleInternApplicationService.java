package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.RequestDownloadUrlDTO;
import com.example.kok.enumeration.RequestStatus;

import java.util.List;

public interface ConsoleInternApplicationService {
    //    지원자 상세 내용
    ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId);

    //    지원자 목록 (공고 ID로 조회)
    List<ConsoleInternApplicantDTO> getApplicantsByNoticeId(Long internNoticeId);

    //    여러 명의 지원자 상세 조회
    List<ConsoleInternApplicantDTO> getApplicationsDetailsByMemberIds(Long internId, List<Long> memberIdList);

    //    전체 지원자 파일 다운로드 URL 생성
    RequestDownloadUrlDTO getApplicationFileInfo(List<Long> memberIdList, Long internNoticeId);

    //    지원자 상태 변경
    public void updateApplicantStatus(Long userId, Long internNoticeId, RequestStatus requestStatus);
}