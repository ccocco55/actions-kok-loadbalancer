package com.example.kok.service;

import com.example.kok.dto.AdminReportCriteriaDTO;
import com.example.kok.dto.AdminReportDTO;

public interface AdminReportService {
//    신고 게시글 목록
    public AdminReportCriteriaDTO getReportList (int page);

//    신고 게시글 상세
    public AdminReportDTO getReportDetail (Long id);

//    신고 게시글 삭제
    public void deleteReportPost(Long id);
}
