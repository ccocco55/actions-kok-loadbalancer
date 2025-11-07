package com.example.kok.service;

import com.example.kok.dto.AdminReportCriteriaDTO;
import com.example.kok.dto.AdminReportDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.repository.AdminReportDAO;
import com.example.kok.repository.CommunityPostFileDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminReportServiceImpl implements AdminReportService {
    private final AdminReportDAO adminReportDAO;
    private final CommunityPostFileDAO communityPostFileDAO;
    private final S3Service s3Service;

//    신고 게시글 목록
    @Override
    public AdminReportCriteriaDTO getReportList(int page) {
        AdminReportCriteriaDTO adminReportCriteriaDTO = new AdminReportCriteriaDTO();
        Criteria criteria = new Criteria(page, adminReportDAO.reportCount());
        List<AdminReportDTO> reports = adminReportDAO.reportList(criteria);
        reports.forEach((report) -> {
            String relativeDate = DateUtils.getCreatedDate(report.getCreatedDateTime());
            report.setRelativeDate(relativeDate);
        });

        criteria.setHasMore(reports.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            reports.remove(reports.size()-1);
        }

        adminReportCriteriaDTO.setAdminReportDTO(reports);
        adminReportCriteriaDTO.setCriteria(criteria);
        return adminReportCriteriaDTO;
    }

//    신고 게시글 상세
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "adminReport", key = "'adminReport_' + #id")
    public AdminReportDTO getReportDetail(Long id) {
        AdminReportDTO adminReportDTO = adminReportDAO.reportDetail(id);

        String relativeDate = DateUtils.getCreatedDate(adminReportDTO.getCreatedDateTime());
        adminReportDTO.setRelativeDate(relativeDate);

        List<PostFileDTO> files = communityPostFileDAO.findAllByPostId(adminReportDTO.getPostId());
        files.forEach((file) -> {
            file.setPostFilePath(s3Service.getPreSignedUrl(file.getPostFilePath(), Duration.ofMinutes(5)));
        });
        adminReportDTO.setPostFiles(files);

        return adminReportDTO;
    }

//    신고 게시글 삭제
    @Override
    @CacheEvict(value = "adminReport", key = "'adminReport_' + #id")
    public void deleteReportPost(Long id) {
        AdminReportDTO adminReportDTO = adminReportDAO.reportDetail(id);

        List<PostFileDTO> postFiles = communityPostFileDAO.findAllByPostId(adminReportDTO.getPostId());
        postFiles.forEach((postFile) -> {
            s3Service.deleteFile(postFile.getPostFilePath());
        });

        adminReportDAO.deleteReport(id);
    }
}
