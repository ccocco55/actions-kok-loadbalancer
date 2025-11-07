package com.example.kok.repository;

import com.example.kok.dto.ReportDTO;
import com.example.kok.mapper.CommunityReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommunityReportDAO {
    private final CommunityReportMapper communityReportMapper;

//    게시글 신고
    public void save(ReportDTO reportDTO) {
        communityReportMapper.insertReport(reportDTO);
    }

//    신고 여부 확인
    public boolean existsReport(Long postId, Long memberId) {
        return communityReportMapper.existsReport(postId, memberId);
    }
}
