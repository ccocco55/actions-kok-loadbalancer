package com.example.kok.controller;

import com.example.kok.aop.aspect.annotation.LogReturnStatus;
import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsoleExperienceMemberDTO;
import com.example.kok.service.ConsoleMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console")
@RequiredArgsConstructor
public class ConsoleMainController {
    private final ConsoleMainService consoleMainService;

//    기업 콘솔 홈
    @GetMapping
    @LogReturnStatus
    public String goToHome(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        // 체험 공고
        int activeCount = consoleMainService.getActiveExperienceNoticeCount(companyId);
        int totalCount = consoleMainService.getAllExperienceNoticeCount(companyId);

        // 인턴 공고
        int activeInternCount = consoleMainService.getActiveInternNoticeCount(companyId);
        int totalInternCount = consoleMainService.getAllInternNoticeCount(companyId);

        List<ConsoleExperienceMemberDTO> recentMembers = consoleMainService.getRecentExperienceMembers(companyId);

        model.addAttribute("activeCount", activeCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("activeInternCount", activeInternCount);
        model.addAttribute("totalInternCount", totalInternCount);
        model.addAttribute("recentMembers", recentMembers);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/main/console-home";
    }
}
