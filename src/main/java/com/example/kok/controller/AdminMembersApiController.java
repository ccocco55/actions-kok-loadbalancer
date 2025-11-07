package com.example.kok.controller;

import com.example.kok.dto.AdminMemberCriteriaDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.service.MemberService;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/member/")
@RequiredArgsConstructor
public class AdminMembersApiController implements AdminMembersApiControllerDocs {
    @Autowired
    private MemberService memberService;

    @GetMapping("list/{page}")
    public ResponseEntity<?> findUserMembers(@PathVariable("page") int page, @RequestParam(required = false) String keyword) {

        AdminMemberCriteriaDTO adminMemberCriteriaDTO = memberService.findUserMembers(page, keyword);
        if (adminMemberCriteriaDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(adminMemberCriteriaDTO);
        }

        return ResponseEntity.ok(adminMemberCriteriaDTO);

    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> findUserMemberById(@PathVariable("id") Long id) {
        UserMemberDTO userMemberDTO = memberService.findMembersByMemberId(id);
        return ResponseEntity.ok(userMemberDTO);
    }

}
