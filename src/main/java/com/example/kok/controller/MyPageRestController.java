package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mypage/**")
@RequiredArgsConstructor
public class MyPageRestController implements MyPageRestControllerDocs {
    private final MemberService memberService;
    private final ExperienceNoticeService experienceNoticeService;

//    저장한 체험 공고
    @GetMapping("/saved-exp")
    public ResponseEntity getSavedExp(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        long memberId=customUserDetails.getId();
        List<ExperienceNoticeDTO> list=memberService.findExperienceNoticeByMemberId(memberId);

        return ResponseEntity.ok(list);
    }

    //    저장한 인턴 공고
    @GetMapping("/saved-int")
    public ResponseEntity getSavedInt(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        long memberId=customUserDetails.getId();
        List<InternNoticeDTO> list=memberService.findInternNoticeByMemberId(memberId);

        return ResponseEntity.ok(list);
    }

//    지원한 체험 공고
    @GetMapping("/request-list")
    public ResponseEntity<?> getRequests(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestExperienceDTO> req=memberService.findRequestExperienceByMemberId(memberId);

        return ResponseEntity.ok(req);
    }

//    체험 지원 취소
    @DeleteMapping("/delete-request/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable("requestId") Long requestId) {
        memberService.deleteRequestExperience(requestId);
        return ResponseEntity.ok().build();
    }

//    지원한 인턴 공고
    @GetMapping("/intern-list")
    public ResponseEntity<?> getInterns(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<RequestInternDTO> req=memberService.findRequestInternByMemberId(memberId);

        return ResponseEntity.ok(req);
    }

    //    인턴 지원 취소
    @DeleteMapping("/delete-intern/{requestId}")
    public ResponseEntity<?> deleteIntern(@PathVariable("requestId") Long requestId) {
        memberService.deleteRequestIntern(requestId);
        return ResponseEntity.ok().build();
    }

//    게시글
    @GetMapping("/post-list")
    public ResponseEntity<?> getPosts(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PostDTO> posts=memberService.findPostsByMemberId(memberId);

        return ResponseEntity.ok(posts);
    }

//    결제 내역
    @GetMapping("/payment-list")
    public ResponseEntity<?> getPayments(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        List<PaymentDTO> payments=memberService.findPaymentByMemberId(memberId);

        return ResponseEntity.ok(payments);
    }

//    프로필 편집 누르면 뜨는 정보들
    @GetMapping("/profile-load")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        Optional<UserMemberDTO> member=memberService.findProfileByMemberId(memberId);

//        System.out.println("이름: "+member.get().getUserName());
//        System.out.println("인포: "+member.get().getMemberInfo());

        if(member.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member.get());
    }

//    프로필 삭제
    @PostMapping("/profile-delete")
    public ResponseEntity<?> deleteProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        long memberId=customUserDetails.getId();
        memberService.deleteProfile(memberId);
        return ResponseEntity.ok().build();
    }

//    프로필 편집 완료
    @PostMapping("/profile-update")
    public void updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @RequestParam(value="profileImgInput", required=false) MultipartFile file,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="job", required=false) String job,
                              @RequestParam(value="info", required=false) String info){
        long memberId=customUserDetails.getId();
        UserMemberDTO member=new UserMemberDTO();
        member.setId(memberId);
        member.setUserName(name);
        member.setJobName(job);
        member.setMemberInfo(info);
//        System.out.println("컨트롤러 인포: " + member.getMemberInfo());
//        System.out.println("컨트롤러 직군: " + member.getJobName());
//        System.out.println(file.isEmpty());
        memberService.updateProfile(memberId, member, file);
    }
}
