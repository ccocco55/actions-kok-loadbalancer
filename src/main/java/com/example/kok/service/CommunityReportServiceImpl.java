package com.example.kok.service;

import com.example.kok.dto.ReportDTO;
import com.example.kok.repository.CommunityReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityReportServiceImpl implements CommunityReportService {
    private final CommunityReportDAO communityReportDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
//    실행되는 동안 오류 발생 시 롤백하기 위한 선언
    public boolean reportPost(Long postId, Long memberId) {
        if (communityReportDAO.existsReport(postId, memberId)) {
//            log.info("이미 신고한 게시글: postId={}, memberId={}", postId, memberId);
            return true;
        }

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setPostId(postId);
        reportDTO.setMemberId(memberId);

        communityReportDAO.save(reportDTO);
//        log.info("신고 완료: postId={}, memberId={}", postId, memberId);
        return false;
    }
}
