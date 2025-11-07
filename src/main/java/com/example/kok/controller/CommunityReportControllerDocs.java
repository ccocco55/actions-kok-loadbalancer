package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "PostReport", description = "게시글 신고 API")
public interface CommunityReportControllerDocs {

//    게시글 신고
    @Operation(summary = "게시글 신고", description = "로그인한 사용자가 특정 게시글을 신고합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "신고 할 게시글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<String> reportPost(@PathVariable Long postId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails);
}
