package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/experience")
@RequiredArgsConstructor
public class ConsoleExperienceNoticeController {
    private final ConsoleExperienceNoticeService consoleExperienceNoticeService;
    private final ConsoleExperienceDetailService consoleExperienceDetailService;
    private final ConsoleExperienceApplicationService consoleExperienceApplicationService;
    private final UserService userService;
    private final ExperienceNoticeService experienceNoticeService;
    private final HttpServletRequest request;

//    기업 콘솔 체험 공고 목록
    @GetMapping("/list")
    public String goToList(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           Model model) {

        String companyName = customUserDetails.getCompanyName();
        model.addAttribute("companyName", companyName);

        return "enterprise-console/experience/console-experience-list";
    }

//    기업 콘솔 체험 공고 등록, 수정
    @GetMapping(value = {"/create", "edit/{id}"})
    public String goToWrite(@PathVariable(required = false) Long id,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails,
                            @ModelAttribute("notice") ConsoleExperienceNoticeRequestDTO consoleExperienceNoticeRequestDTO,
                            Model model) {

        ConsoleExperienceNoticeRequestDTO notice = consoleExperienceNoticeService.getNotice(id);

        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        if(request.getRequestURI().contains("create")){
            model.addAttribute("page","create");
            model.addAttribute("companyName", companyName);

            return "enterprise-console/experience/console-experience-update";
        }

        model.addAttribute("page","edit");
        model.addAttribute("id", id);
        model.addAttribute("notice", notice);
        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);

        return "enterprise-console/experience/console-experience-update";
    }

//    기업 콘솔 체험 지원서 목록
    @GetMapping("/applicate-list/{experienceNoticeId}")
    public String goToApplicateList(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    @PathVariable("experienceNoticeId") Long experienceNoticeId,
                                    Model model) {

        ConsoleExperienceNoticeDTO experienceDetail = consoleExperienceDetailService.getDetail(experienceNoticeId);

        List<ConsoleExperienceApplicantDTO> applicants  =
                consoleExperienceApplicationService.getApplicationsByNoticeId(experienceNoticeId);

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("experienceDetail", experienceDetail);
        model.addAttribute("experienceNoticeId", experienceNoticeId);
        model.addAttribute("companyName", companyName);

        if (applicants != null && !applicants.isEmpty()) {//지원자 id
            ConsoleExperienceApplicantDTO firstApplicant = applicants.get(0);
            model.addAttribute("memberId", firstApplicant.getUserId());
        }

        return "enterprise-console/experience/console-experience-applicate-list";
    }

//    기업 콘솔 체험 지원서
    @GetMapping("/application/{experienceNoticeId}/{memberId}")
    public String goToApplication(@PathVariable("experienceNoticeId") Long experienceNoticeId,
                                  @PathVariable("memberId") Long memberId,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  Model model) {

        ConsoleExperienceApplicantDTO applicantDetail =
                consoleExperienceApplicationService.getApplicationsDetail(memberId, experienceNoticeId);

        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        model.addAttribute("applicantDetail", applicantDetail);
        model.addAttribute("memberId", memberId); //지원자 id
        model.addAttribute("companyName", companyName);


        return "enterprise-console/experience/console-experience-application";
    }

//    기업 콘솔 평가서
    @GetMapping("/review")
    public String goToReview(@RequestParam("experienceNoticeId") Long experienceNoticeId,
                                  @RequestParam("memberId") Long memberId,
                             @RequestParam("requestExperienceId") Long requestExperienceId,
                             @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        model.addAttribute("memberId", memberId);
        model.addAttribute("experienceNoticeId", experienceNoticeId);
        model.addAttribute("requestExperienceId", requestExperienceId);
        UserDTO user=new UserDTO();
        user=userService.findById(memberId);
        ExperienceNoticeDTO exp=new ExperienceNoticeDTO();
        exp=experienceNoticeService.findNoticeById(experienceNoticeId);
        model.addAttribute("user", user);
        model.addAttribute("exp", exp);
        Long companyId=customUserDetails.getId();
        model.addAttribute("companyId", companyId);

        return "enterprise-console/review/console-review";
    }

    @PostMapping(value = {"/create", "/edit"})
    public RedirectView write(ConsoleExperienceNoticeRequestDTO consoleExperienceNoticeRequestDTO,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails){
        consoleExperienceNoticeRequestDTO.setCompanyId(customUserDetails.getId());
        consoleExperienceNoticeService.createOrEdit(request.getRequestURI().contains("create"), consoleExperienceNoticeRequestDTO);
        return new RedirectView("/enterprise-console/experience/list");
    }


}
