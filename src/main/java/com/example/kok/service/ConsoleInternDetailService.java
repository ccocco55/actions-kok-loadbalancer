package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.enumeration.RequestStatus;

public interface ConsoleInternDetailService {
//    공고 상세 내용
    ConsoleInternNoticeDTO getDetail(Long internNoticeId);

//    지원 목록
    public ConsoleInternApplicantCriteriaDTO getApplicateList(Long companyId, int page, RequestStatus status);
}
