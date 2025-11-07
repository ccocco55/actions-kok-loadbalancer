package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name="MyPage", description = "MyPage Rest API")
public interface MyPageRestControllerDocs {
    @Operation(summary = "저장된 체험 공고 목록",
            description = "사용자가 저장하기를 누른 체험 공고 목록 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity getSavedExp(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "저장된 인턴 공고 목록",
            description = "사용자가 저장하기를 누른 인턴 공고 목록 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity getSavedInt(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "지원한 체험 공고 목록",
            description = "사용자가 지원한 체험 공고 목록 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity<?> getRequests(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "체험 공고 지원 취소",
            description = "체험 지원을 취소함",
            parameters = {
                @Parameter(name = "requestId", description = "지원 취소할 지원서 id")
            }
    )
    public ResponseEntity<?> deleteRequest(@PathVariable("requestId") Long requestId);
    
    @Operation(summary = "지원한 인턴 공고 목록",
            description = "사용자가 지원한 인턴 공고 목록 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity<?> getInterns(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "인턴 공고 지원 취소",
            description = "인턴 지원을 취소함",
            parameters = {
                @Parameter(name = "requestId", description = "지원 취소할 지원서 id")
            }
    )
    public ResponseEntity<?> deleteIntern(@PathVariable("requestId") Long requestId);
    
    @Operation(summary = "게시글 목록",
            description = "사용자가 작성한 게시글 목록 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity<?> getPosts(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "결제 내역",
            description = "사용자의 결제 내역 조회",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity<?> getPayments(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "프로필 정보",
            description = "프로필 변경을 눌렀을 때 조회할 유저 정보",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보")
            }
    )
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    
    @Operation(summary = "프로필 업데이트하기",
            description = "사용자의 소개, 이름, 직군, 프로필 사진 등 프로필 업데이트",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 사용자의 정보"),
                @Parameter(name="file", description = "사용자가 입력한 프로필 사진"),
                @Parameter(name="name", description = "사용자가 입력한 이름"),
                @Parameter(name="job", description = "사용자가 선택한 직군 카테고리 이름"),
                @Parameter(name="info", description = "사용자가 입력한 소개")
            }
    )
    @PostMapping("/profile-update")
    public void updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @RequestParam(value="profileImgInput", required=false) MultipartFile file,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="job", required=false) String job,
                              @RequestParam(value="info", required=false) String info);
    
    
    
}
