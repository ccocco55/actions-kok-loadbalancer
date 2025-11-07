package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.enumeration.UserRole;
import com.example.kok.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@Slf4j
@RequiredArgsConstructor
public class CommunityPostController implements CommunityPostControllerDocs{
    private final CommunityPostService communityPostService;

//    게시글 목록 조회
    @GetMapping("/{page}")
    public ResponseEntity<PostsCriteriaDTO> getPosts(@PathVariable("page") int page,
                                                     @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long memberId = (customUserDetails != null) ? customUserDetails.getId() : null;
        PostsCriteriaDTO list = communityPostService.getList(page, memberId);
        return ResponseEntity.ok(list);
    }

//    게시글 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable("id") Long id,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null || customUserDetails.getUserRole() == UserRole.COMPANY) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Long memberId = customUserDetails.getId();
        PostDTO postDTO = communityPostService.getPost(id, memberId);
        return ResponseEntity.ok(postDTO);
    }

//    게시글 작성
    @PostMapping
    public ResponseEntity<PostDTO> write(@RequestParam("postContent") String postContent,
                                   @RequestParam(value="files", required=false) List<MultipartFile> files,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostContent(postContent);
        postDTO.setMemberId(customUserDetails.getId());

        communityPostService.write(postDTO, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

//    게시글 수정
    @PostMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable("id") Long id,
                                    @RequestParam("postContent") String postContent,
                                    @RequestParam(value="deleteFiles", required=false) Long[] deleteFiles,
                                    @RequestParam(value="files", required=false) List<MultipartFile> files,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setPostContent(postContent);
        postDTO.setMemberId(customUserDetails.getId());

        PostDTO updatedPost = communityPostService.update(postDTO, deleteFiles, files);
        return ResponseEntity.ok(updatedPost);
    }

//    게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> remove(@PathVariable("id") Long id) {
        communityPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
