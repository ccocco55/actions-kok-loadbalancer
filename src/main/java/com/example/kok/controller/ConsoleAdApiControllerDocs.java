package com.example.kok.controller;

import com.example.kok.dto.ConsoleAdNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Enterprise Console - Advertisement", description = "기업 콘솔 광고 API")
public interface ConsoleAdApiControllerDocs {
    @Operation(summary = "광고 목록 조회", description = "특정 기업의 광고 목록을 페이지 단위로 조회합니다.")
    ResponseEntity<ConsoleAdNoticeCriteriaDTO> list(
            @Parameter(description = "기업 ID") @PathVariable("companyId") Long companyId,
            @Parameter(description = "페이지 번호") @PathVariable("page") int page,
            @Parameter(description = "검색 키워드") @RequestParam(required = false) String keyword
    );

    @Operation(summary = "광고 등록", description = "새로운 광고를 등록합니다. 파일 업로드 포함 가능.")
    ResponseEntity<?> registerAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles
    );

    @Operation(summary = "광고 수정", description = "기존 광고를 수정합니다. 파일 수정 포함 가능.")
    ResponseEntity<?> updateAdvertisement(
            @Parameter(description = "광고 ID") @PathVariable("id") Long id,
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles
    );

    @Operation(summary = "광고 상태 변경", description = "광고의 상태(active/inactive)를 변경합니다.")
    ResponseEntity<?> updateNoticeStatus(
            @Parameter(description = "광고 ID") @PathVariable("id") Long id,
            @RequestBody ConsoleAdNoticeDTO consoleAdDTO
    );

    @Operation(summary = "광고 삭제", description = "광고를 삭제합니다.")
    ResponseEntity<?> deleteAd(
            @Parameter(description = "광고 ID") @PathVariable("id") Long advertisementId
    );

}