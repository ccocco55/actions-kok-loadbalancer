package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.enumeration.UserRole;
import com.example.kok.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
@Slf4j
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{companyId}")
    public ResponseEntity<?> follow(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long companyId) {
        if(userDetails == null || userDetails.getUserRole() == UserRole.COMPANY) {
            return ResponseEntity.ok(false);
        }
        followService.follow(userDetails.getId(), companyId);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long companyId) {
        followService.unfollow(userDetails.getId(), companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Boolean> isFollowing(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long companyId) {
        if(userDetails == null) {
            return ResponseEntity.ok(false);
        }
        boolean result = followService.isFollowing(userDetails.getId(), companyId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/count/{companyId}")
    public ResponseEntity<Integer> countFollowers(@PathVariable Long companyId) {
        int count = followService.countFollowers(companyId);
        return ResponseEntity.ok(count);
    }
}
