package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.enumeration.RequestStatus;

public interface ConsoleExperienceDetailService {
//    공고 상세 내용
    ConsoleExperienceNoticeDTO getDetail(Long experienceNoticeId);

//    지원 목록
    public ConsoleExperienceApplicantCriteriaDTO getApplicateList(Long companyId, int page, RequestStatus status);
}
