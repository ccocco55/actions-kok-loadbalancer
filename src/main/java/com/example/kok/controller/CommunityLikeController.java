package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostLikeDTO;
import com.example.kok.enumeration.UserRole;
import com.example.kok.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class CommunityLikeController implements CommunityLikeControllerDocs{
    private final CommunityLikeService communityLikeService;

//    게시글 좋아요
    @PostMapping
    public ResponseEntity<PostLikeDTO> postLike(@RequestBody PostLikeDTO postLikeDTO,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if(customUserDetails == null || customUserDetails.getUserRole() == UserRole.COMPANY) {
            return ResponseEntity.status(403).build();
        }

        postLikeDTO.setMemberId(customUserDetails.getId());
        communityLikeService.postLike(postLikeDTO);
        return ResponseEntity.ok(postLikeDTO);
    }

//    게시글 좋아요 취소
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostLikeDTO> removePostLike(@PathVariable Long postId,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long memberId = customUserDetails.getId();
        communityLikeService.removePostLike(postId, memberId);
        return ResponseEntity.ok().build();
    }


    // 좋아요 갯수 조회
    @GetMapping("/{postId}/count")
    public ResponseEntity<Long> getPostLikeCount(@PathVariable Long postId) {
        Long count = (long) communityLikeService.getPostLikeCount(postId);
        return ResponseEntity.ok(count);
    }

    // 좋아요 여부 확인
    @GetMapping("/{postId}/check")
    public ResponseEntity<Boolean> checkedPostLike(@PathVariable Long postId,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long memberId = customUserDetails.getId();
        Boolean liked = communityLikeService.checkedPostLike(postId, memberId);
        return ResponseEntity.ok(liked);
    }
}
