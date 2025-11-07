package com.example.kok.repository;

import com.example.kok.dto.AdminReportDTO;
import com.example.kok.mapper.AdminReportMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminReportDAO {
    private final AdminReportMapper adminReportMapper;

//    신고 게시글 목록
    public List<AdminReportDTO> reportList (Criteria criteria) {
        return adminReportMapper.selectReportAll(criteria);
    }

//    신고 게시글 개수
    public int reportCount () {
        return adminReportMapper.selectReportCount();
    }

//    신고 게시글 상세
    public AdminReportDTO reportDetail(Long id) {
        return adminReportMapper.selectReportDetail(id);
    }

//    신고 게시글 삭제
    public void deleteReport(Long id) {
        adminReportMapper.deleteReportPost(id);
    }
}
