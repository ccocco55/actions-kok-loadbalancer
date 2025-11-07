package com.example.kok.controller;

import com.example.kok.aop.aspect.annotation.LogReturnStatus;
import com.example.kok.aop.aspect.annotation.LogStatus;
import com.example.kok.dto.*;
import com.example.kok.service.ConsoleAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/ad")
public class ConsoleAdApiController implements ConsoleAdApiControllerDocs {
    private final ConsoleAdService adService;

//    목록
    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<ConsoleAdNoticeCriteriaDTO> list(@PathVariable("companyId") Long companyId,
                                  @PathVariable("page") int page,
                                  @RequestParam(required = false) String keyword) {

        ConsoleAdNoticeCriteriaDTO adCriteriaDTO = adService.getList(companyId, page, keyword);
        if (adCriteriaDTO == null || adCriteriaDTO.getAdLists().size() == 0) {
            return ResponseEntity.ok(adCriteriaDTO);
        }

        return ResponseEntity.ok(adCriteriaDTO);
    }

//    등록
    @PostMapping("/create")
    @LogStatus
    public ResponseEntity<?> registerAdvertisement(
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles) {

        adService.registerAdvertisement(adNoticeDTO, multipartFiles);

        return ResponseEntity.ok("");
    }

//    수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAdvertisement(
            @PathVariable("id") Long id,
            @ModelAttribute ConsoleAdNoticeDTO adNoticeDTO,
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles) {

        adNoticeDTO.setId(id);
        adService.updateAdvertisement(adNoticeDTO, multipartFiles);

        return ResponseEntity.ok("");
    }

//    광고 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateNoticeStatus(@PathVariable("id") Long id,
                                                @RequestBody ConsoleAdNoticeDTO consoleAdDTO) {
        adService.updateListStatus(id, consoleAdDTO.getAdvertisementStatus());

        return ResponseEntity.ok(consoleAdDTO);
    }

//    삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable("id") Long advertisementId) {
        adService.deleteAdvertisement(advertisementId);
        return ResponseEntity.ok("success");
    }

}