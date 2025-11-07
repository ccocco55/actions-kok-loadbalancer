package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.RequestExperienceDAO;
import com.example.kok.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/experience")
public class ConsoleExperienceNoticeApiController implements ConsoleExperienceNoticeApiControllerDocs {
    private final ConsoleExperienceNoticeService experienceService;
    private final ConsoleExperienceDetailService experienceDetailService;
    private final ConsoleExperienceApplicationService consoleExperienceApplicationService;
    private final EvaluationService evaluationService;
    private final RequestExperienceDAO requestExperienceDAO;

    //    공고 목록
    @GetMapping("/list/{page}")
    public ResponseEntity<ConsoleExperienceNoticeCriteriaDTO> list(
            @PathVariable("page") int page,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(required = false) String keyword,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model) {

        Long companyId = customUserDetails.getId();
        ConsoleExperienceNoticeCriteriaDTO experienceCriteriaDTO = experienceService.getList(companyId, page, status, keyword);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getExperienceLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        model.addAttribute("companyName", companyName);

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

    //    공고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                                @RequestBody ConsoleExperienceNoticeDTO consoleExperienceDTO) {
        experienceService.updateListStatus(id, consoleExperienceDTO.getExperienceNoticeStatus());
        return ResponseEntity.ok(consoleExperienceDTO);
    }

//    공고 등록
//    @PostMapping("/create")
//    @LogStatus
//    public ResponseEntity<?> createNotice(@RequestBody ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
//        experienceService.registerNotice(noticeRequestDTO);
//
//        return ResponseEntity.ok(noticeRequestDTO);
//    }

//    공고 수정
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<?> updateNotice(@PathVariable Long id,
//                                          @RequestBody ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
//        noticeRequestDTO.setId(id);
//        experienceService.modifyNotice(noticeRequestDTO);
//
//        return ResponseEntity.ok(noticeRequestDTO);
//
//    }

    //    공고 상세 - 지원자
    @GetMapping("/applicate-list/{experienceNoticeId}/{page}")
    public ResponseEntity<ConsoleExperienceApplicantCriteriaDTO> applicateList(@PathVariable("experienceNoticeId") Long experienceNoticeId,
                                                                               @PathVariable("page") int page,
                                                                               @RequestParam(required = false) RequestStatus status,
                                                                               @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                               Model model) {
        ConsoleExperienceApplicantCriteriaDTO experienceCriteriaDTO = experienceDetailService.getApplicateList(experienceNoticeId, page, status);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getApplicantLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        model.addAttribute("companyName", companyName);

        return ResponseEntity.ok(experienceCriteriaDTO);
    }

    //    삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok("success");
    }

    //    여러 명의 지원자 파일
    @PostMapping("/{experienceNoticeId}/applications/files")
    public ResponseEntity<RequestDownloadUrlDTO> getApplicantsFileUrls(
            @PathVariable Long experienceNoticeId,
            @RequestBody List<Long> memberIdList) {
        return ResponseEntity.ok(consoleExperienceApplicationService.getApplicationFileInfo(memberIdList, experienceNoticeId));
    }

    //    지원자 상태 변경
    @PutMapping("/applicant/{id}/status")
    public ResponseEntity<?> updateApplicantStatus(
            @PathVariable("id") Long userId,
            @RequestBody ConsoleExperienceApplicantDTO applicantDTO) {

        consoleExperienceApplicationService.updateApplicantStatus(userId, applicantDTO.getExperienceNoticeId(), applicantDTO.getRequestExperienceStatus());
        return ResponseEntity.ok("success");
    }

    //    평가할 수 있는지 여부
    @GetMapping("/isEvalOk")
    public ResponseEntity<Boolean> isEvalOk(@RequestParam("experienceNoticeId") Long experienceNoticeId,
                                            @RequestParam("memberId") Long memberId) {
        Long requestExperienceId=requestExperienceDAO.findId(memberId, experienceNoticeId);
        boolean result=consoleExperienceApplicationService.isEvalOk(experienceNoticeId, memberId, requestExperienceId);
        return ResponseEntity.ok(result);
    }

    //    평가하기
    @PostMapping("/go-review")
    @ResponseBody
    public void goReview(@RequestBody EvaluationDTO evaluation) {
        evaluationService.review(evaluation);
    }

}
