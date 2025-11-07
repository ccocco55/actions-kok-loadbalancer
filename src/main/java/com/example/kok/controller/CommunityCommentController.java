package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.kok.dto.CommentDTO;
import com.example.kok.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentController implements CommunityCommentControllerDocs{
    private final CommunityCommentService communityCommentService;

//    댓글 작성
    @PostMapping
    public ResponseEntity<CommentDTO> write(@RequestBody CommentDTO commentDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        commentDTO.setMemberId(customUserDetails.getId());
        commentDTO.setMemberProfileUrl(customUserDetails.getMemberProfileUrl());
        communityCommentService.write(commentDTO);
        return ResponseEntity.ok(commentDTO);
    }
//    댓글 목록
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDTO>> list(@PathVariable Long postId,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long memberId = customUserDetails.getId();
        List<CommentDTO> comments = communityCommentService.getComments(postId, memberId);
        return ResponseEntity.ok(comments);
    }

//    댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("commentId") Long commentId,
                                           @RequestBody CommentDTO commentDTO,
                                           @AuthenticationPrincipal CustomUserDetails user) {
        commentDTO.setId(commentId);
        commentDTO.setMemberProfileUrl(user.getMemberProfileUrl());
        commentDTO.setMemberId(user.getId());
        communityCommentService.update(commentDTO);
        return ResponseEntity.ok().build();
    }

//    댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable("commentId") Long commentId) {
        communityCommentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }


}
