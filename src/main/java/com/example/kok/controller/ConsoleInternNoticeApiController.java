package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.service.ConsoleInternApplicationService;
import com.example.kok.service.ConsoleInternDetailService;
import com.example.kok.service.ConsoleInternNoticeService;
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
@RequestMapping("/api/enterprise-console/intern")
public class ConsoleInternNoticeApiController implements ConsoleInternNoticeApiControllerDocs {
    private final ConsoleInternNoticeService internService;
    private final ConsoleInternDetailService internDetailService;
    private final ConsoleInternApplicationService consoleInternApplicationService;

//    공고 목록
    @GetMapping("/list/{page}")
    public ResponseEntity<ConsoleInternNoticeCriteriaDTO> list(
            @PathVariable("page") int page,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(required = false) String keyword,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model) {

        Long companyId = customUserDetails.getId();
        ConsoleInternNoticeCriteriaDTO internCriteriaDTO = internService.getList(companyId, page, status, keyword);
        if(internCriteriaDTO == null || internCriteriaDTO.getInternLists().size() == 0){
            return ResponseEntity.ok(internCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        model.addAttribute("companyName", companyName);

        return ResponseEntity.ok(internCriteriaDTO);
    }

//    공고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                          @RequestBody ConsoleInternNoticeDTO consoleInternDTO) {
        internService.updateListStatus(id, consoleInternDTO.getInternNoticeStatus());
        return ResponseEntity.ok(consoleInternDTO);
    }

////    공고 등록
//    @PostMapping("/create")
//    @LogStatus
//    public ResponseEntity<?> createNotice(@RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO) {
//        internService.registerNotice(noticeRequestDTO);
//
//        return ResponseEntity.ok(noticeRequestDTO);
//    }
//
////    공고 수정
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<?> updateNotice(@PathVariable Long id,
//                                          @RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO) {
//        noticeRequestDTO.setId(id);
//        internService.modifyNotice(noticeRequestDTO);
//
//        return ResponseEntity.ok(noticeRequestDTO);
//    }

//    공고 상세 - 지원자
    @GetMapping("/applicate-list/{internNoticeId}/{page}")
    public ResponseEntity<ConsoleInternApplicantCriteriaDTO> applicateList(@PathVariable("internNoticeId") Long internNoticeId,
                                                                           @PathVariable("page") int page,
                                                                           @RequestParam(value = "status", required = false) RequestStatus status,
                                                                           @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                           Model model) {

        ConsoleInternApplicantCriteriaDTO internCriteriaDTO = internDetailService.getApplicateList(internNoticeId, page, status);
        if(internCriteriaDTO == null || internCriteriaDTO.getApplicantLists().size() == 0){
            return ResponseEntity.ok(internCriteriaDTO);
        }

        String companyName = customUserDetails.getCompanyName();
        model.addAttribute("companyName", companyName);

        return ResponseEntity.ok(internCriteriaDTO);
    }

//    삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
        internService.deleteIntern(id);
        return ResponseEntity.ok("success");
    }

//    여러 명의 지원자 파일
    @PostMapping("/{internNoticeId}/applications/files")
    public ResponseEntity<RequestDownloadUrlDTO> getApplicantsFileUrls(
            @PathVariable Long internNoticeId,
            @RequestBody List<Long> memberIdList) {
        return ResponseEntity.ok(consoleInternApplicationService.getApplicationFileInfo(memberIdList, internNoticeId));
    }

//    지원자 상태 변경
    @PutMapping("/applicant/{id}/status")
    public ResponseEntity<?> updateApplicantStatus(
            @PathVariable("id") Long userId,
            @RequestBody ConsoleInternApplicantDTO applicantDTO) {

        consoleInternApplicationService.updateApplicantStatus(userId, applicantDTO.getInternNoticeId(), applicantDTO.getRequestInternStatus());
        return ResponseEntity.ok("success");
    }

}
