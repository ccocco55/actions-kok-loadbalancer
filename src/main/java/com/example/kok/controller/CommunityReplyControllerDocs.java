package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Reply", description = "대댓글 API")
public interface CommunityReplyControllerDocs {

//    대댓글 작성
    @Operation(summary = "대댓글 작성", description = "특정 댓글에 대한 대댓글을 작성합니다.",
            parameters = {
                    @Parameter(name = "replyDTO", description = "작성할 대댓글의 정보가 담긴 객체"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<ReplyDTO> write(@RequestBody ReplyDTO replyDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    대댓글 목록
    @Operation(summary = "대댓글 목록 조회", description = "특정 댓글에 달린 대댓글 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "commentId", description = "댓글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<List<ReplyDTO>> list(@PathVariable Long commentId,
                                               @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    대댓글 수정
    @Operation(summary = "대댓글 수정", description = "작성한 대댓글의 내용을 수정합니다.",
            parameters = {
                    @Parameter(name = "replyId", description = "대댓글 ID"),
                    @Parameter(name = "replyDTO", description = "수정할 대댓글의 정보가 담긴 객체"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<ReplyDTO> updateReply(@PathVariable("replyId") Long replyId,
                                         @RequestBody ReplyDTO replyDTO,
                                         @AuthenticationPrincipal CustomUserDetails user);

//    대댓글 삭제
    @Operation(summary = "대댓글 삭제", description = "특정 대댓글을 삭제합니다.",
            parameters = {
                    @Parameter(name = "replyId", description = "대댓글 ID")
            }
    )
    public ResponseEntity<ReplyDTO> deleteReply(@PathVariable("replyId") Long replyId);
}
