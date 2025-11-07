package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestDownloadUrlDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleExperienceApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceApplicationServiceImpl implements ConsoleExperienceApplicationService {
    private final ConsoleExperienceApplicationDAO consoleExperienceApplicationDAO;
    private final S3Service s3Service;
    private final EvaluationService evaluationService;

    @Override
    public ConsoleExperienceApplicantDTO getApplicationsDetail(Long memberId, Long experienceNoticeId) {
        return consoleExperienceApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);
    }

    //    목록
    @Override
    public List<ConsoleExperienceApplicantDTO> getApplicationsByNoticeId(Long experienceNoticeId) {
        return consoleExperienceApplicationDAO.findApplicationsByNoticeId(experienceNoticeId);
    }

    //    여러 명의 지원자 상세 조회
    @Override
    public List<ConsoleExperienceApplicantDTO> getApplicationsDetailsByMemberIds(Long experienceNoticeId, List<Long> memberIdList) {
        List<ConsoleExperienceApplicantDTO> results = new ArrayList<>();

        for (Long memberId : memberIdList) {
            // 기존 단일 조회 재활용
            ConsoleExperienceApplicantDTO applicantDetail =
                    consoleExperienceApplicationDAO.findApplicantDetail(memberId, experienceNoticeId);

            // 파일 정보 조회
            FileDTO fileInfo = consoleExperienceApplicationDAO
                    .findResumeFileByMemberId(memberId, experienceNoticeId)
                    .orElse(null);

            if (fileInfo != null) {
                applicantDetail.setFilePath(fileInfo.getFilePath());
                applicantDetail.setFileName(fileInfo.getFileOriginName());
            }

            results.add(applicantDetail);
        }

        return results;
    }

    //    평가하기 가능 여부 조회
    public boolean isEvalOk(Long experienceNoticeId, Long memberId, Long requestExperienceId) {
        RequestExperienceDTO exp=consoleExperienceApplicationDAO.findEvalOk(experienceNoticeId, memberId);
        boolean requested=evaluationService.isReviewedThis(memberId, requestExperienceId);
        LocalDate now = LocalDate.now();
        System.out.println("accept: "+exp.getRequestExperienceStatus());
        System.out.println("time: "+ exp.getExperienceEndDate().isBefore(now));
        if(exp.getRequestExperienceStatus().equals(RequestStatus.ACCEPT)&&exp.getExperienceEndDate().isBefore(now)&&!requested){
            return true;
        } else{
            return false;
        }
    }

    //    지원자 상태 변경
    @Override
    public void updateApplicantStatus(Long userId, Long experienceNoticeId, RequestStatus requestExperienceStatus) {
        consoleExperienceApplicationDAO.updateApplicantStatus(userId, experienceNoticeId, requestExperienceStatus);
    }

    @Override
    public RequestDownloadUrlDTO getApplicationFileInfo(List<Long> memberIdList, Long experienceNoticeId) {
        RequestDownloadUrlDTO requestDownloadUrlDTO = new RequestDownloadUrlDTO();
        List<String> downloadUrls = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        getApplicationsDetailsByMemberIds(experienceNoticeId, memberIdList).forEach(applicantDetail -> {
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
