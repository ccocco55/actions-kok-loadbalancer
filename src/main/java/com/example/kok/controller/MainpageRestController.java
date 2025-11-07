package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.repository.*;
import com.example.kok.service.CompanyService;
import com.example.kok.service.MainpageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/main/**")
@RequiredArgsConstructor
@Slf4j
public class MainpageRestController implements MainpageRestControllerDocs {
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestInternDAO requestInternDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;
    private final InternNoticeDAO internNoticeDAO;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;
    private final MemberDAO memberDAO;
    private final MainpageService  mainpageService;


    @GetMapping("popular")
    public List<CompanyDTO> findPopularCompany() {
            log.info(mainpageService.findPopularCompanies().toString());
        return mainpageService.findPopularCompanies() ;
    }

    @GetMapping("requestExperience")
    public List<RequestExperienceDTO> findRequestExperience(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long experienceId) {

        return mainpageService.findRequestExperienceByCompanyId(customUserDetails.getId(), experienceId);
    }

    @GetMapping("requestIntern")
    public List<RequestInternDTO> findRequestIntern(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long internId) {
        return mainpageService.findRequestInternByCompanyId(customUserDetails.getId(), internId);
    }

    //    체험 목록
    @GetMapping("experience")
    public List<ExperienceNoticeDTO> getExperience(@RequestParam(required = false) String keyword) {
        log.info("experience search = {}", keyword);
        return mainpageService.findExperienceNotices(keyword);
    }

    //    체험 목록
    @GetMapping("intern")
    public List<InternNoticeDTO> getIntern(@RequestParam(required = false) String keyword) {
        log.info("intern search = {}", keyword);
        return mainpageService.findInternNotices(keyword);
    }

    //    멤버 알람 세팅 조회
    @GetMapping("alarm")
    public MemberAlarmSettingDTO findAllByMemberId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if(customUserDetails == null) {
            return null;
        }
        return memberAlarmSettingDAO.findAllByMemberId(customUserDetails.getId());
    }

    @PatchMapping("active")
    public void updateActive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword) {

        memberAlarmSettingDAO.updateByKeywordToActive(customUserDetails.getId(), keyword.get("keyword"));
    }
    @PatchMapping("inactive")
    public void updateInactive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword) {

        memberAlarmSettingDAO.updateByKeywordToInactive(customUserDetails.getId(), keyword.get("keyword"));
    }

    @GetMapping("link")
    public List<MemberDTO> find(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        if(customUserDetails == null) {
            return null;
        }
        log.info(memberDAO.findLink(customUserDetails.getUserPhone()).toString());
        return memberDAO.findLink(customUserDetails.getUserPhone());
    }

}
