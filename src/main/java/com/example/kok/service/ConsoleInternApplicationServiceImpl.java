package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.RequestDownloadUrlDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleInternApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleInternApplicationServiceImpl implements ConsoleInternApplicationService {
    private final ConsoleInternApplicationDAO consoleInternApplicationDAO;
    private final S3Service s3Service;

    @Override
    public ConsoleInternApplicantDTO getApplicantDetail(Long memberId, Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantDetail(memberId, internNoticeId);

    }

    //    목록
    @Override
    public List<ConsoleInternApplicantDTO> getApplicantsByNoticeId(Long internNoticeId) {
        return consoleInternApplicationDAO.findApplicantsByNoticeId(internNoticeId);
    }

    //    여러 명의 지원자 상세 조회
    @Override
    public List<ConsoleInternApplicantDTO> getApplicationsDetailsByMemberIds(Long internNoticeId, List<Long> memberIdList) {
        List<ConsoleInternApplicantDTO> results = new ArrayList<>();

        for (Long memberId : memberIdList) {
            // 기존 단일 조회 재활용
            ConsoleInternApplicantDTO applicantDetail =
                    consoleInternApplicationDAO.findApplicantDetail(memberId, internNoticeId);

            // 파일 정보 조회
            FileDTO fileInfo = consoleInternApplicationDAO
                    .findResumeFileByMemberId(memberId, internNoticeId)
                    .orElse(null);

            if (fileInfo != null) {
                applicantDetail.setFilePath(fileInfo.getFilePath());
                applicantDetail.setFileName(fileInfo.getFileOriginName());
            }

            results.add(applicantDetail);
        }

        return results;
    }

    //    지원자 상태 변경
    @Override
    public void updateApplicantStatus(Long userId, Long internNoticeId, RequestStatus requestInternStatus) {
        consoleInternApplicationDAO.updateApplicantStatus(userId, internNoticeId, requestInternStatus);
    }

    @Override
    public RequestDownloadUrlDTO getApplicationFileInfo(List<Long> memberIdList, Long internNoticeId) {
        RequestDownloadUrlDTO requestDownloadUrlDTO = new RequestDownloadUrlDTO();
        List<String> downloadUrls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        getApplicationsDetailsByMemberIds(internNoticeId, memberIdList).forEach(applicantDetail -> {
            if (applicantDetail.getFilePath() != null && applicantDetail.getFilePath() != null) {
                String downloadUrl = s3Service.getPreSignedDownloadUrl(
                        applicantDetail.getFilePath(),
                        applicantDetail.getFileName(),
                        Duration.ofMinutes(5)
                );
                downloadUrls.add(downloadUrl);
                fileNames.add(applicantDetail.getFileName());
            }
        });

        requestDownloadUrlDTO.setUrls(downloadUrls);
        requestDownloadUrlDTO.setFileNames(fileNames);
        return requestDownloadUrlDTO;
    }

}
