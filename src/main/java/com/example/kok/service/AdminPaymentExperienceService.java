package com.example.kok.service;

import com.example.kok.dto.AdminPaymentExperienceCriteriaDTO;

public interface AdminPaymentExperienceService {
//    체험 목록
    public AdminPaymentExperienceCriteriaDTO getPaymentExperienceList(int page, String keyword, String category);
}
