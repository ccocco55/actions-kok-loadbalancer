package com.example.kok.controller;

import com.example.kok.dto.AdimPostWarningDTO;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.service.AdminWarningMemberService;
import com.example.kok.service.CommunityPostService;
import com.example.kok.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/warning/")
@RequiredArgsConstructor
public class AdminWarningMemberApiController {
    @Autowired
    private AdminWarningMemberService adminWarningMemberService;
    @Autowired
    private CommunityPostService communityPostService;
    @Autowired
    private UserService userService;
    
//    전체 게시물 가져오기
    @GetMapping("all-posts")
    public ResponseEntity<?> findAllPosts() {
        communityPostService.findAllPosts();
        return ResponseEntity.ok(communityPostService.findAllPosts());
    }

//    강제 삭제 게시물 수 가져오기
    @GetMapping("count/{id}")
    public ResponseEntity<?> banPostCount(@PathVariable("id") Long id) {
        int count = adminWarningMemberService.banPostCountByMemberId(id);

        return ResponseEntity.ok(count);

    }

//    게시물 디테일 가져오기
    @GetMapping("detail/{id}")
    public ResponseEntity<?> findPostById(@PathVariable("id") Long id) {
        PostDTO postDTO = adminWarningMemberService.findById(id);
        return ResponseEntity.ok(postDTO);
    }

//    유저 찾기
    @GetMapping("member/{Id}")
    public ResponseEntity<?> findMemberById(@PathVariable("Id") Long Id) {
        UserDTO userDTO = userService.findById(Id);
        return ResponseEntity.ok(userDTO);
    }

//    주의 게시물 가져오기
    @GetMapping("list/{page}")
    public ResponseEntity<?> findUserMembers(@PathVariable("page") int page, @RequestParam(required = false) List<Integer> ids , @RequestParam(required = false) String keyword) {

        AdimPostWarningDTO adimPostWarningDTO = adminWarningMemberService.findWarningPosts(page, ids, keyword);
        if (adimPostWarningDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(adimPostWarningDTO);
        }

        return ResponseEntity.ok(adimPostWarningDTO);

    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        adminWarningMemberService.postSoftDelete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id) {
        adminWarningMemberService.postWarningStatusHold(id);

        return ResponseEntity.ok().build();
    }

    

}
