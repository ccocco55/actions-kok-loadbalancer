package com.example.kok.repository;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleInternApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsoleInternApplicationDAO {
    private final ConsoleInternApplicationMapper consoleInternMapper;

//    지원자 상세 내용
    public ConsoleInternApplicantDTO findApplicantDetail(Long memberId, Long internNoticeId) {
        return consoleInternMapper.selectApplicationDetail(memberId, internNoticeId);
    }

    public Optional<FileDTO> findResumeFileByMemberId(Long memberId, Long internNoticeId) {
        return consoleInternMapper.selectResumeFileByMemberId(memberId, internNoticeId);
    }

//    공고별 지원자 목록 조회
    public List<ConsoleInternApplicantDTO> findApplicantsByNoticeId(Long internNoticeId) {
        return consoleInternMapper.selectApplicantsByNoticeId(internNoticeId);
    }

//    지원자 상태 변경
    public void updateApplicantStatus(Long userId, Long internNoticeId, RequestStatus requestInternStatus) {
        consoleInternMapper.updateApplicantStatus(userId, internNoticeId, requestInternStatus);
    }


}
