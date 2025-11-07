package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.dto.InternNoticeCriteriaDTO;
import com.example.kok.service.AdvertisementService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.InternNoticeService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/intern/**")
@RequiredArgsConstructor
public class InternController {
    private final ExperienceNoticeService experienceNoticeService;
    private final AdvertisementService advertisementService;
    //    인턴 공고 목록으로 이동
    @GetMapping("list")
    public String goToIntList(Model model,
                              @RequestParam(required = false) String sharedCompanyId,
                              @RequestParam(required = false) String sharedInternId,
                              @ModelAttribute("search") Search search) {
        model.addAttribute("search", search);
        model.addAttribute("sharedCompanyId", sharedCompanyId);
        model.addAttribute("sharedInternId", sharedInternId);
        List<AdvertisementDTO> advertisements = advertisementService.getAllAdvertisements();
        model.addAttribute("advertisements", advertisements);
        String banner=experienceNoticeService.getBanner();
        model.addAttribute("banner",banner);
        return "intern/list";
    }
}
