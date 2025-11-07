package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ReplyDTO;
import com.example.kok.service.CommunityReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Slf4j
public class CommunityReplyController implements CommunityReplyControllerDocs{
    private final CommunityReplyService communityReplyService;

//    대댓글 작성
    @PostMapping
    public ResponseEntity<ReplyDTO> write(@RequestBody ReplyDTO replyDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        replyDTO.setMemberId(customUserDetails.getId());
        communityReplyService.write(replyDTO);
        return ResponseEntity.ok(replyDTO);
    }

//    대댓글 목록
    @GetMapping("/{commentId}")
    public ResponseEntity<List<ReplyDTO>> list(@PathVariable Long commentId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long memberId = customUserDetails.getId();
        List<ReplyDTO> replies = communityReplyService.getReplies(commentId, memberId);
        return ResponseEntity.ok(replies);
    }

//    대댓글 수정
    @PutMapping("/{replyId}")
    public ResponseEntity<ReplyDTO> updateReply(@PathVariable("replyId") Long replyId,
                                         @RequestBody ReplyDTO replyDTO,
                                         @AuthenticationPrincipal CustomUserDetails user) {

//        log.info("user = {}", user);
//        log.info("replyId = {}", replyId);
//        log.info("replyDTO = {}", replyDTO);
        replyDTO.setId(replyId);
        replyDTO.setMemberId(user.getId());
        communityReplyService.update(replyDTO);
        return ResponseEntity.ok().build();
    }

//    대댓글 삭제
    @DeleteMapping("/{replyId}")
    public ResponseEntity<ReplyDTO> deleteReply(@PathVariable("replyId") Long replyId) {
        communityReplyService.delete(replyId);
        return ResponseEntity.noContent().build();
    }

}
