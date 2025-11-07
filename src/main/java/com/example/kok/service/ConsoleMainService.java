package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceMemberDTO;

import java.util.List;

public interface ConsoleMainService {
    // 활성화된 체험 공고 수
    int getActiveExperienceNoticeCount(Long companyId);

    // 전체 체험 공고 수
    int getAllExperienceNoticeCount(Long companyId);

    int getActiveInternNoticeCount(Long companyId);

    int getAllInternNoticeCount(Long companyId);

    // 지원한 회원 목록
    List<ConsoleExperienceMemberDTO> getRecentExperienceMembers(Long companyId);
}
