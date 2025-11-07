package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.repository.CompanyProfileFileDAO;
import com.example.kok.repository.EvaluationDAO;
import com.example.kok.service.*;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/interns/**")
@RequiredArgsConstructor
public class InternRestController implements InternRestControllerDocs {
    private final InternNoticeService internNoticeService;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final CompanyService companyService;
    private final RequestInternService requestInternService;
    private final UserService userService;
    private final EvaluationService evaluationService;

    //    목록
    @GetMapping("/{page}")
    public ResponseEntity<?> intList(@PathVariable("page") int page, Search search) {
//        System.out.println("#######################################################");
//        System.out.println(page);
        InternNoticeCriteriaDTO internNoticeCriteriaDTO = internNoticeService.selectAllInternNotice(page, search);
        if(internNoticeCriteriaDTO.getInterns().size()==0||internNoticeCriteriaDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(internNoticeCriteriaDTO);
    }

    //    프로필 사진 url
    @GetMapping("/profile")
    public String profile(Long companyId){
        CompanyProfileFileDTO profile=companyProfileFileDAO.findFileByCompanyId(companyId);
        if(profile==null){
            return "/images/member/profile.png";
        }
        internNoticeService.setPreSignedUrl(profile);
        return profile.getFilePath();
    }

    //    상세 불러오기
    @GetMapping("/detail")
    public Map<String,Object> detail(Long companyId, Long internId) {
        Map<String,Object> result = new HashMap<>();
        result.put("notice", internNoticeService.findNoticeById(internId));
        result.put("company", companyService.findCompanyById(companyId));
        return result;
    }

////    간편지원 넣기
//    @PostMapping("/request")
//    public void requestIntern(Long internId,
//            @AuthenticationPrincipal CustomUserDetails customUserDetails,
//            @RequestParam List<Long> fileIds) {
//
//        RequestInternDTO reqDTO = new RequestInternDTO();
//        reqDTO.setInternNoticeId(internId);
//        reqDTO.setMemberId(customUserDetails.getId());
    ////        reqDTO.setMemberAlarmSettingId();
//        requestInternService.applyForIntern(reqDTO, fileIds);
//    }

//    공고 저장하기
    @PostMapping("/save")
    public ResponseEntity<?> saveIntern(@RequestParam Long internId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if(customUserDetails!=null){
            SaveInternNoticeDTO saveInt=new SaveInternNoticeDTO();
            saveInt.setInternNoticeId(internId);
            saveInt.setMemberId(customUserDetails.getId());
            internNoticeService.saveInt(saveInt);
            return ResponseEntity.ok("저장 성공");
        }
        return ResponseEntity.notFound().build();
    }

    //    공고 저장 취소하기
    @PostMapping("/unsave")
    public void unsaveIntern(@RequestParam Long internId,
                             @AuthenticationPrincipal CustomUserDetails customUserDetails){
        SaveInternNoticeDTO deleteInt=new SaveInternNoticeDTO();
        deleteInt.setInternNoticeId(internId);
        deleteInt.setMemberId(customUserDetails.getId());
        internNoticeService.deleteInt(deleteInt);
    }

    //    저장 여부 판별
    @GetMapping("/is-saved")
    public boolean isSaved(@RequestParam Long internId,
                           @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if(customUserDetails!=null){
            System.out.println(customUserDetails);
            SaveInternNoticeDTO interns=new SaveInternNoticeDTO();
            interns.setInternNoticeId(internId);
            interns.setMemberId(customUserDetails.getId());
            boolean result= internNoticeService.isSavedInt(interns);
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
            System.out.println(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    //    지원 여부 판별
    @GetMapping("/is-requested")
    public boolean isRequested(@RequestParam Long internId,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if(customUserDetails!=null){
            RequestInternDTO intern=new RequestInternDTO();
            intern.setMemberId(customUserDetails.getId());
            intern.setInternNoticeId(internId);
            boolean result=requestInternService.isRequested(intern);
            System.out.println("컨트롤러: "+result);
            return result;
        }
        return false;
    }

    //    간편지원 완료
    @PostMapping("/request")
    public void requestIntern(@RequestBody RequestInternDTO requestInternDTO,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        System.out.println(requestInternDTO);
//        System.out.println(customUserDetails.getId());
        RequestInternDTO request=new RequestInternDTO();
        request.setRequestInternMemberName(requestInternDTO.getRequestInternMemberName());
        request.setRequestInternMemberEmail(requestInternDTO.getRequestInternMemberEmail());
        request.setRequestInternMemberPhone(requestInternDTO.getRequestInternMemberPhone());
        if(requestInternDTO.getRequestInternMemberUrl()!=null){
            request.setRequestInternMemberUrl(requestInternDTO.getRequestInternMemberUrl());
        }
        request.setFileId(requestInternDTO.getFileId());
        request.setMemberId(customUserDetails.getId());
        request.setInternNoticeId(requestInternDTO.getInternNoticeId());
        System.out.println(request);
        requestInternService.applyForIntern(request);
    }

    //    평가 있는지 여부
    @GetMapping("/is-reviewed")
    public boolean isRequested(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boolean result = evaluationService.isReviewed(customUserDetails.getId());
        return result;
    }
}
