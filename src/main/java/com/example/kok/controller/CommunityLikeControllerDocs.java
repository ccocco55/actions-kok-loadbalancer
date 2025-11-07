package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostLikeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "PostLike", description = "게시글 좋아요 API")
public interface CommunityLikeControllerDocs {

//    게시글 좋아요
    @Operation(summary = "게시글 좋아요 등록", description = "게시글에 좋아요를 등록합니다.",
            parameters = {
                    @Parameter(name = "postLikeDTO", description = "좋아요할 게시글의 정보가 담긴 객체"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<PostLikeDTO> postLike(@RequestBody PostLikeDTO postLikeDTO,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    게시글 좋아요 취소
    @Operation(summary = "게시글 좋아요 취소", description = "게시글의 좋아요를 취소합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "좋아요를 취소할 게시글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<PostLikeDTO> removePostLike(@PathVariable Long postId,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    좋아요 갯수 조회
    @Operation(summary = "게시글 좋아요 갯수 조회", description = "해당 게시글의 좋아요 총 갯수를 반환합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "좋아요 갯수를 조회할 게시글 ID")
            }
    )
    public ResponseEntity<Long> getPostLikeCount(@PathVariable Long postId);

//    좋아요 여부 확인
    @Operation(summary = "게시글 좋아요 여부 확인", description = "현재 로그인한 회원이 해당 게시글에 좋아요를 눌렀는지 여부를 반환합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "좋아요 여부를 확인할 게시글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<Boolean> checkedPostLike(@PathVariable Long postId,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails);

}
