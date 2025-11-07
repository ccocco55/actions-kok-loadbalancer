package com.example.kok.service;

import com.example.kok.dto.AdminInternNoticeDetailCriteriaDTO;
import com.example.kok.dto.AdminInternNoticeDetailDTO;
import com.example.kok.dto.AdminInternNoticeListCriteriaDTO;

public interface AdminEmployService {
//    인턴 공고 목록
    public AdminInternNoticeListCriteriaDTO getList(int page, String keyword);

//    인턴 공고 - 상세정보
    public AdminInternNoticeDetailDTO getDetail(Long id);
}
