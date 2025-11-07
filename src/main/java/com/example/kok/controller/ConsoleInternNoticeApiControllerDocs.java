package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Enterprise Console - Intern", description = "기업 콘솔 인턴 공고 API")
public interface ConsoleInternNoticeApiControllerDocs {
    @Operation(summary = "인턴 공고 목록 조회", description = "기업 ID, 상태, 키워드 조건으로 인턴 공고 목록을 페이지 단위로 조회합니다.")
    ResponseEntity<ConsoleInternNoticeCriteriaDTO> list(
            @Parameter(description = "페이지 번호") @PathVariable("page") int page,
            @Parameter(description = "공고 상태") @RequestParam(value = "status", required = false) Status status,
            @Parameter(description = "검색 키워드") @RequestParam(required = false) String keyword,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    );

    @Operation(summary = "인턴 공고 상태 변경", description = "인턴 공고의 상태(active/inactive)를 변경합니다.")
    ResponseEntity<?> updateNoticeStatus(
            @Parameter(description = "공고 ID") @PathVariable("id") Long id,
            @RequestBody ConsoleInternNoticeDTO consoleInternDTO
    );

//    @Operation(summary = "인턴 공고 등록", description = "새로운 인턴 공고를 등록합니다.")
//    ResponseEntity<?> createNotice(
//            @RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO
//    );
//
//    @Operation(summary = "인턴 공고 수정", description = "기존 인턴 공고를 수정합니다.")
//    ResponseEntity<?> updateNotice(
//            @Parameter(description = "공고 ID") @PathVariable("id") Long id,
//            @RequestBody ConsoleInternNoticeRequestDTO noticeRequestDTO
//    );

    @Operation(summary = "인턴 공고 지원자 목록 조회", description = "특정 인턴 공고의 지원자 목록을 페이지 단위로 조회합니다.")
    ResponseEntity<ConsoleInternApplicantCriteriaDTO> applicateList(
            @Parameter(description = "인턴 공고 ID") @PathVariable("internNoticeId") Long internNoticeId,
            @Parameter(description = "페이지 번호") @PathVariable("page") int page,
            @Parameter(description = "지원 상태") @RequestParam(value = "status", required = false) RequestStatus status,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    );

    @Operation(summary = "인턴 공고 삭제", description = "특정 인턴 공고를 삭제합니다.")
    ResponseEntity<?> deleteNotice(
            @Parameter(description = "공고 ID") @PathVariable("id") Long id
    );

    @Operation(summary = "여러 명의 지원자 파일 다운로드 URL 조회", description = "체험 공고에 지원한 여러 명의 지원자 파일 다운로드 URL을 조회합니다.")
    ResponseEntity<RequestDownloadUrlDTO> getApplicantsFileUrls(
            @Parameter(description = "체험 공고 ID") @PathVariable("internNoticeId") Long internNoticeId,
            @RequestBody List<Long> memberIdList
    );

    @Operation(summary = "지원자 상태 변경", description = "특정 인턴 지원자의 상태를 변경합니다.")
    ResponseEntity<?> updateApplicantStatus(
            @Parameter(description = "지원자 ID") @PathVariable("id") Long userId,
            @RequestBody ConsoleInternApplicantDTO applicantDTO
    );
}
