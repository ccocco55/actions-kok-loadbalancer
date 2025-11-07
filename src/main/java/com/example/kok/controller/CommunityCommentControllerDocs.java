package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CommentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Comment", description = "댓글 API")
public interface CommunityCommentControllerDocs {

//    댓글 작성
    @Operation(summary = "댓글 작성", description = "특정 게시글에 댓글을 작성합니다.",
            parameters = {
                    @Parameter(name = "commentDTO", description = "작성할 댓글의 정보가 담긴 객체"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<CommentDTO> write(@RequestBody CommentDTO commentDTO,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    댓글 목록
    @Operation(summary = "댓글 목록 조회", description = "특정 게시글에 달린 댓글 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "게시글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<List<CommentDTO>> list(@PathVariable Long postId,
                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    댓글 수정
    @Operation(summary = "댓글 수정", description = "작성한 댓글 내용을 수정합니다.",
            parameters = {
                    @Parameter(name = "commentId", description = "댓글 ID"),
                    @Parameter(name = "commentDTO", description = "수정할 댓글의 정보가 담긴 객체"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("commentId") Long commentId,
                                           @RequestBody CommentDTO commentDTO,
                                           @AuthenticationPrincipal CustomUserDetails user);

//    댓글 삭제
    @Operation(summary = "댓글 삭제", description = "작성한 댓글을 삭제합니다.",
            parameters = {
                    @Parameter(name = "commentId", description = "댓글 ID")
            }
    )
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable("commentId") Long commentId);
}
