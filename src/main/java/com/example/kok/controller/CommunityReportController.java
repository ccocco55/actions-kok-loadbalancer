package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.enumeration.UserRole;
import com.example.kok.service.CommunityReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@Slf4j
@RequiredArgsConstructor
public class CommunityReportController implements CommunityReportControllerDocs{
    private final CommunityReportService communityReportService;

    @PostMapping("/{postId}")
    public ResponseEntity<String> reportPost(@PathVariable Long postId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails == null || customUserDetails.getUserRole() == UserRole.COMPANY) {
            return ResponseEntity.status(403).body("일반 회원만 이용할 수 있습니다.");
        }

        Long memberId = customUserDetails.getId();
        boolean existReported = communityReportService.reportPost(postId, memberId);

        if (existReported) {
            return ResponseEntity.badRequest().body("이미 신고한 게시글입니다.");
        }
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }
}
