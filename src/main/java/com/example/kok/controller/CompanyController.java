package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.enumeration.UserRole;
import com.example.kok.service.AdvertisementService;
import com.example.kok.service.CompanyService;
import com.example.kok.service.UserService;
import com.example.kok.util.CompanySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;
    private final AdvertisementService advertisementService;

//    기업 목록
    @GetMapping("/list")
    public String goToCompanyList(Model model, CompanySearch search, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long userId = null;

        if (customUserDetails != null) {
            userId = customUserDetails.getId();
            model.addAttribute("isMember", customUserDetails.getUserRole() == UserRole.MEMBER);
            model.addAttribute("isCompany", customUserDetails.getUserRole() == UserRole.COMPANY);

            if (customUserDetails.getUserRole() == UserRole.COMPANY) {
                CompanyDTO companyDTO = companyService.findCompanyById(customUserDetails.getId());
                model.addAttribute("companyDTO", companyDTO);
            }
        }

        List<AdvertisementDTO> advertisements = advertisementService.getAllAdvertisements();
        model.addAttribute("advertisements", advertisements);

        model.addAttribute("companies", companyService.getCompanyList(1, search, userId).getCompanies());
        return "company/list";
    }

//    기업 상세
    @GetMapping("/{companyId}")
    public String detailPage(@PathVariable Long companyId, Model model) {

        CompanyDTO company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);

        List<CompanyDTO> popularCompanies = companyService.getCompaniesByFollowerCount();
        model.addAttribute("popularCompanies", popularCompanies);

        List<AdvertisementDTO> advertisements = advertisementService.getAllAdvertisements();
        model.addAttribute("advertisements", advertisements);

        return "company/detail";
    }
}
