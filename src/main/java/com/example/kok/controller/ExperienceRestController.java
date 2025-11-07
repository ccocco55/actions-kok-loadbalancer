package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.repository.CompanyProfileFileDAO;
import com.example.kok.service.*;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/experiences/**")
@RequiredArgsConstructor
public class ExperienceRestController implements ExperienceRestControllerDocs{
    private final ExperienceNoticeService experienceNoticeService;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final CompanyService companyService;
    private final RequestExperienceService requestExperienceService;
    private final UserService userService;

    //    목록
    @GetMapping("{page}")
    public ResponseEntity<?> expList(@PathVariable("page") int page, Search search) {
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = experienceNoticeService.selectAllExperienceNotice(page, search);
        if(experienceNoticeCriteriaDTO.getExperiences().size()==0||experienceNoticeCriteriaDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(experienceNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(experienceNoticeCriteriaDTO);
    }

    //    프로필 사진 url
    @GetMapping("/profile")
    public String profile(Long companyId){
        CompanyProfileFileDTO profile=companyProfileFileDAO.findFileByCompanyId(companyId);
        if(profile==null){
            return "/images/member/profile.png";
        }
        experienceNoticeService.setPreSignedUrl(profile);
        return profile.getFilePath();
    }

    //    상세 불러오기
    @GetMapping("/detail")
    public Map<String,Object> detail(Long companyId, Long experienceId) {
        Map<String,Object> result = new HashMap<>();
        result.put("notice", experienceNoticeService.findNoticeById(experienceId));
        result.put("company", companyService.findCompanyById(companyId));
        return result;
    }

////    간편지원 넣기
//    @PostMapping("/request")
//    public void requestExperience(Long experienceId,
//            @AuthenticationPrincipal CustomUserDetails customUserDetails,
//            @RequestParam List<Long> fileIds) {
//
//        RequestExperienceDTO reqDTO = new RequestExperienceDTO();
//        reqDTO.setExperienceNoticeId(experienceId);
//        reqDTO.setMemberId(customUserDetails.getId());
    ////        reqDTO.setMemberAlarmSettingId();
//        requestExperienceService.applyForExperience(reqDTO, fileIds);
//    }

//    공고 저장하기
    @PostMapping("/save")
    public ResponseEntity<?> saveExperience(@RequestParam Long experienceId,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if(customUserDetails!=null){
            SaveExperienceNoticeDTO saveExp=new SaveExperienceNoticeDTO();
            saveExp.setExperienceNoticeId(experienceId);
            saveExp.setMemberId(customUserDetails.getId());
            experienceNoticeService.saveExp(saveExp);
            return ResponseEntity.ok("저장 성공");
        }
        return ResponseEntity.notFound().build();
    }

    //    공고 저장 취소하기
    @PostMapping("/unsave")
    public void unsaveExperience(@RequestParam Long experienceId,
                                 @AuthenticationPrincipal CustomUserDetails customUserDetails){
        SaveExperienceNoticeDTO deleteExp=new SaveExperienceNoticeDTO();
        deleteExp.setExperienceNoticeId(experienceId);
        deleteExp.setMemberId(customUserDetails.getId());
        experienceNoticeService.deleteExp(deleteExp);
    }

    //    지원 여부 판별
    @GetMapping("is-requested")
    public boolean isRequested(@RequestParam Long experienceId,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if(customUserDetails!=null){
            RequestExperienceDTO req=new RequestExperienceDTO();
            req.setMemberId(customUserDetails.getId());
            req.setExperienceNoticeId(experienceId);
            boolean result=experienceNoticeService.isRequested(req);

//            System.out.println("체험공고id: "+experienceId);
//            System.out.println("멤버id: "+customUserDetails.getId());
//            System.out.println(experienceNoticeService.isRequested(req));
            return result;
        }
        return false;
    }

    //    저장 여부 판별
    @GetMapping("/is-saved")
    public boolean isSaved(@RequestParam Long experienceId,
                           @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if(customUserDetails!=null){
//            System.out.println(customUserDetails);
            SaveExperienceNoticeDTO exp=new SaveExperienceNoticeDTO();
            exp.setExperienceNoticeId(experienceId);
            exp.setMemberId(customUserDetails.getId());
            boolean result= experienceNoticeService.isSavedExp(exp);
            return result;
        }
        return false;

    }

    //    간편지원 input에 넣을 유저 정보 불러오기
    @GetMapping("/user")
    public ResponseEntity<UserDTO> loadUserDetails(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if(customUserDetails!=null){
            UserDTO user=new UserDTO();
            user=userService.findById(customUserDetails.getId());
//            System.out.println(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    //    간편지원 완료
    @PostMapping("/request")
    public void requestExperience(@RequestBody RequestExperienceDTO requestExperienceDTO,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        System.out.println(requestExperienceDTO);
//        System.out.println(customUserDetails.getId());
        RequestExperienceDTO request=new RequestExperienceDTO();
        request.setRequestExperienceMemberName(requestExperienceDTO.getRequestExperienceMemberName());
        request.setRequestExperienceMemberEmail(requestExperienceDTO.getRequestExperienceMemberEmail());
        request.setRequestExperienceMemberPhone(requestExperienceDTO.getRequestExperienceMemberPhone());
        if(requestExperienceDTO.getRequestExperienceMemberUrl()!=null){
            request.setRequestExperienceMemberUrl(requestExperienceDTO.getRequestExperienceMemberUrl());
        }
        request.setFileId(requestExperienceDTO.getFileId());
        request.setMemberId(customUserDetails.getId());
        request.setExperienceNoticeId(requestExperienceDTO.getExperienceNoticeId());
//        System.out.println(request);
        requestExperienceService.applyForExperience(request);
    }
}
