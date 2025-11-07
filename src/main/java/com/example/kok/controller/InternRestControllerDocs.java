package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.util.Search;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Tag(name="Intern", description = "Intern Rest API")
public interface InternRestControllerDocs {
    @Operation(summary = "인턴 공고 목록",
            description = "키워드가 포함된 인턴 공고 목록을 가져옴.",
            parameters = {
                @Parameter(name = "page", description = "현재 페이지"),
                @Parameter(name="search", description = "화면에서 입력한 키워드")
            }
    )
    public ResponseEntity<?> intList(@PathVariable("page") int page, Search search);

    @Operation(summary = "기업 프로필 사진 url",
            description = "기업 id로 체험 공고를 올린 각 기업의 프로필 사진 url을 가져옴.",
            parameters = {
                @Parameter(name = "companyId", description = "해당 기업의 id")
            }
    )
    public String profile(Long companyId);

    @Operation(summary = "인턴 공고 상세",
            description = "기업 id와 공고의 id로 해당 인턴 공고와 공고를 올린 기업의 상세 내용을 가져옴.",
            parameters = {
                @Parameter(name = "companyId", description = "기업의 id"),
                @Parameter(name="internId", description = "해당 공고의 id")
            }
    )
    public Map<String,Object> detail(Long companyId, Long internId);

    @Operation(summary = "인턴 공고 저장",
            description = "현재 로그인한 멤버의 id와 해당 공고의 id로 공고 저장",
            parameters = {
                @Parameter(name = "internId", description = "해당 공고의 id"),
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public ResponseEntity<?> saveIntern(@RequestParam Long internId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "인턴 공고 저장 취소",
            description = "현재 로그인한 멤버의 id와 해당 공고의 id로 공고 저장 취소",
            parameters = {
                @Parameter(name = "internId", description = "해당 공고의 id"),
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public void unsaveIntern(@RequestParam Long internId,
             @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "체험 공고 저장 여부",
            description = "현재 로그인한 멤버의 id와 해당 공고의 id로 공고 저장 여부 판별",
            parameters = {
                @Parameter(name = "internId", description = "해당 공고의 id"),
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public boolean isSaved(@RequestParam Long internId,
                           @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "유저 정보 조회",
            description = "현재 로그인한 유저의 정보를 가져와 간편지원 기본 정보에 담음.",
            parameters = {
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public ResponseEntity<UserDTO> loadUserDetails(@AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "인턴 공고 지원 여부 판별",
            description = "현재 로그인한 멤버의 id와 해당 공고의 id로 해당 공고에 지원했는지 판별",
            parameters = {
                @Parameter(name = "internId", description = "해당 공고의 id"),
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public boolean isRequested(@RequestParam Long internId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "간편지원하기",
            description = "현재 로그인한 멤버의 id와 간편지원 팝업에 입력한 정보로 간편지원하기.",
            parameters = {
                @Parameter(name = "requestInternDTO", description = "간편지원 팝업에서 입력한 정보들"),
                @Parameter(name="customUserDetails", description = "현재 로그인한 멤버 조회")
            }
    )
    public void requestIntern(@RequestBody RequestInternDTO requestInternDTO,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails);
}
