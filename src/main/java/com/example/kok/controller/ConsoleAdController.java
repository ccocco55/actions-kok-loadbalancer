package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.service.ConsoleAdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/ad")
@RequiredArgsConstructor
public class ConsoleAdController {
    private final ConsoleAdService consoleAdService;

//    기업 콘솔 광고 목록
    @GetMapping("/list")
    public String goToList(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);
        return "enterprise-console/advertisement/console-add-list";
    }

//    기업 콘솔 광고 등록/수정
    @GetMapping(value = {"/create", "/edit/{id}"})
    public String goToWrite(HttpServletRequest request,
                            @PathVariable(required = false) Long id,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails,
                            Model model) {

        if(request.getRequestURI().contains("create")){
            Long companyId = customUserDetails.getId();
            String companyName = customUserDetails.getCompanyName();
            String memberName = customUserDetails.getUsername();

            model.addAttribute("page","create");
            model.addAttribute("notice", new ConsoleAdNoticeDTO());
            model.addAttribute("companyId", companyId);
            model.addAttribute("companyName", companyName);
            model.addAttribute("memberName", memberName);

            return "enterprise-console/advertisement/console-add-upload";
        }
        ConsoleAdNoticeDTO notice = consoleAdService.getDetail(id);
        consoleAdService.setPreSignedUrl(notice);

        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/advertisement/console-add-upload";
    }

}
