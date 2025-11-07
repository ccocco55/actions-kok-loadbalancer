package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.service.CompanyService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.InternNoticeService;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@Slf4j
@RequiredArgsConstructor
public class CompanyListController implements CompanyListControllerDocs{
    private final CompanyService companyService;
    private final ExperienceNoticeService experienceNoticeService;
    private final InternNoticeService internNoticeService;

//    기업 목록
    @GetMapping("/{page}")
    public CompaniesCriteriaDTO getCompanyList(@PathVariable("page") int page, CompanySearch search, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = null;
        if (customUserDetails != null) {
            userId = customUserDetails.getId();
        }
        return companyService.getCompanyList(page, search, userId);
    }

//    기업 체험 공고 목록
    @GetMapping("/{companyId}/experiences/{page}")
    public CompanyExperienceNoticeCriteriaDTO getCompanyExperienceNotices(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, Search search) {
//        log.info("기업 체험 공고 목록 companyId: {}, page: {}, search: {}", companyId, page, search);
        return experienceNoticeService.getExperienceNoticesByCompanyId(page, companyId, search);
    }

//    기업 인턴 공고 목록
    @GetMapping("/{companyId}/interns/{page}")
    public CompanyInternNoticeCriteriaDTO getCompanyInternNotices(@PathVariable("companyId") Long companyId, @PathVariable("page") int page, Search search) {
//        log.info("기업 인턴 공고 목록 companyId: {}, page: {}, search: {}", companyId, page, search);
        return internNoticeService.getInternNoticesByCompanyId(page, companyId, search);
    }
}
