package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Post", description = "게시글 API")
public interface CommunityPostControllerDocs {

//    게시글 목록
    @Operation(summary = "게시글 목록", description = "커뮤니티 게시글 목록을 페이지 단위로 조회합니다. (무한 스크롤 지원)",
                parameters = {
                    @Parameter(name = "page", description = "조회할 페이지 번호"),
                        @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
                }
    )
    public ResponseEntity<PostsCriteriaDTO> getPosts(@PathVariable("page") int page,
                                                     @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    게시글 상세
    @Operation(summary = "게시글 상세", description = "게시글 ID를 이용해 상세 내용을 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "게시글 ID"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<PostDTO> getOne(@PathVariable("id") Long id,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    게시글 작성
    @Operation(summary = "게시글 작성", description = "일반 회원이 파일 첨부도 가능한 게시글을 작성합니다.",
            parameters = {
                    @Parameter(name = "postContent", description = "게시글 내용"),
                    @Parameter(name = "files", description = "첨부 파일"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<PostDTO> write(@RequestParam("postContent") String postContent,
                                   @RequestParam(value="files", required=false) List<MultipartFile> files,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    게시글 수정
    @Operation(summary = "게시글 수정", description = "기존 게시글 내용을 수정하거나 첨부파일을 추가/삭제합니다.",
            parameters = {
                    @Parameter(name = "id", description = "수정할 게시글 ID"),
                    @Parameter(name = "postContent", description = "수정할 게시글 내용"),
                    @Parameter(name = "deleteFiles", description = "삭제할 게시글 파일"),
                    @Parameter(name = "files", description = "추가할 게시글 파일"),
                    @Parameter(name = "customUserDetails", description = "로그인한 회원 정보")
            }
    )
    public ResponseEntity<PostDTO> update(@PathVariable("id") Long id,
                                @RequestParam("postContent") String postContent,
                                @RequestParam(value="deleteFiles", required=false) Long[] deleteFiles,
                                @RequestParam(value="files", required=false) List<MultipartFile> files,
                                @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글 ID를 이용하여 해당 게시글을 삭제합니다.",
            parameters = {
                    @Parameter(name = "id", description = "삭제할 게시글 ID")
            }
    )
    public ResponseEntity<PostDTO> remove(@PathVariable("id") Long id);
}
