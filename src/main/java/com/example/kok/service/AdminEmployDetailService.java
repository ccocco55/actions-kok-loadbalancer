package com.example.kok.service;

import com.example.kok.dto.AdminInternNoticeDetailCriteriaDTO;

public interface AdminEmployDetailService {
    public AdminInternNoticeDetailCriteriaDTO getDetailList(int page, Long id);
}
