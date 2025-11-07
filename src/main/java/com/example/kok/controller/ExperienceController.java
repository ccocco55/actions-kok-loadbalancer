package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.dto.BannerFileDTO;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.service.AdvertisementService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/experience/**")
@RequiredArgsConstructor
public class ExperienceController {
    private final ExperienceNoticeService experienceNoticeService;
    private final AdvertisementService advertisementService;
//    체험 목록으로 이동
    @GetMapping("list")
    public String goToExpList(Model model,
                              @RequestParam(required = false) String sharedCompanyId,
                              @RequestParam(required = false) String sharedExperienceId,
                              @ModelAttribute("search") Search search) {
        model.addAttribute("search", search);
        model.addAttribute("sharedCompanyId", sharedCompanyId);
        model.addAttribute("sharedExperienceId", sharedExperienceId);
        List<AdvertisementDTO> advertisements = advertisementService.getAllAdvertisements();
        model.addAttribute("advertisements", advertisements);
        String banner=experienceNoticeService.getBanner();
        model.addAttribute("banner",banner);
        return "experience/list";
    }
}
