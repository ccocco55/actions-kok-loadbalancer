package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "CompanyFollow", description = "기업 팔로우 API")
public interface FollowControllerDocs {

//    기업 팔로우
    @Operation(summary = "기업 팔로우", description = "로그인한 사용자가 특정 기업을 팔로우합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "팔로우할 기업 ID")
            }
    )
    public ResponseEntity<?> follow(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @PathVariable Long companyId);

//    기업 팔로우 취소
    @Operation(summary = "기업 팔로우 취소", description = "로그인한 사용자가 팔로우한 특정 기업의 팔로우를 취소합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "팔로우 취소할 기업 ID")
            }
    )
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal CustomUserDetails userDetails,
                                      @PathVariable Long companyId);

//    기업 팔로우 확인
    @Operation(summary = "기업 팔로우 여부 확인", description = "로그인한 사용자가 해당 기업을 팔로우하고 있는지 여부를 반환합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "팔로우 여부를 확인할 기업 ID")
            }
    )
    public ResponseEntity<Boolean> isFollowing(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @PathVariable Long companyId);

//    기업 팔로워 수
    @Operation(summary = "기업 팔로워 수 조회", description = "특정 기업을 팔로우하고 있는 사용자의 총 수를 반환합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "팔로워 수를 조회할 기업 ID")
            }
    )
    public ResponseEntity<Integer> countFollowers(@PathVariable Long companyId);
}
