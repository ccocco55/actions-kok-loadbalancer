package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleExperienceApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceApplicationDAO {
    private final ConsoleExperienceApplicationMapper consoleExperienceMapper;

    //    지원자 상세 내용
    public ConsoleExperienceApplicantDTO findApplicantDetail(Long memberId, Long experienceNoticeId) {
        return consoleExperienceMapper.selectApplicationDetail(memberId, experienceNoticeId);
    }

    //    이력서 단일 다운
    public Optional<FileDTO> findResumeFileByMemberId(Long memberId, Long experienceNoticeId) {
        return consoleExperienceMapper.selectResumeFileByMemberId(memberId, experienceNoticeId);
    }

    //    공고별 지원자 목록 조회
    public List<ConsoleExperienceApplicantDTO> findApplicationsByNoticeId(Long experienceNoticeId) {
        return consoleExperienceMapper.selectApplicantsByNoticeId(experienceNoticeId);
    }

    //    평가하기 가능 여부 판별
    public RequestExperienceDTO findEvalOk(Long experienceNoticeId, Long memberId) {
        return consoleExperienceMapper.selectEvalOk(experienceNoticeId, memberId);
    }

    //    지원자 상태 변경
    public void updateApplicantStatus(Long userId, Long experienceNoticeId, RequestStatus requestExperienceStatus) {
        consoleExperienceMapper.updateApplicantStatus(userId, experienceNoticeId, requestExperienceStatus);
    }

}
