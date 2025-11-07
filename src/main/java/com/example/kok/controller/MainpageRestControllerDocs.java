package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Tag( name ="mainpageFunction", description = "mainpage Function API")
public interface MainpageRestControllerDocs {
    @Operation(summary = "검색창 팔로워 top - 6",
            description = "검색창에 팔로워가 가장 많은 6개 기업을 리턴 합니다."
    )
    public List<CompanyDTO> findPopularCompany();
    @Operation(summary = "체험 지원 내역 조회",
            description = "최근 3개월 간의 체험 지원 내역을 조회합니다.",
            parameters = {
                @Parameter(name="customUserDetails",description = "회원의 id를 불러오기 위한 인증 정보를 불러옵니다."),
                @Parameter(name = "experienceId",description = "체험 지원 내역의 상세 조회를 위한 id 입니다.(필수 입력 x)")
            }
    )
    public List<RequestExperienceDTO> findRequestExperience(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long experienceId);
    @Operation(summary = "인턴 지원 내역 조회",
            description = "최근 3개월 간의 인턴 지원 내역을 조회합니다.",
            parameters = {
                    @Parameter(name="customUserDetails",description = "회원의 id를 불러오기 위한 인증 정보를 불러옵니다"),
                    @Parameter(name = "internId",description = "인턴 지원 내역의 상세 조회를 위한 id 입니다.(필수 입력 x)")
            }
    )
    public List<RequestInternDTO> findRequestIntern(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(required = false) Long internId);
    @Operation(summary = "검색창 체험 조회",
            description = "keyword로 체험을 조회합니다.",
            parameters = {
                    @Parameter(name = "keyword",description = "체험 내용 검색을 위한 keyword 입니다.(필수 입력 x)")
            }
    )
    public List<ExperienceNoticeDTO> getExperience(@RequestParam(required = false) String keyword);
    @Operation(summary = "검색창 인턴 조회",
            description = "keyword로 인턴을 조회합니다.",
            parameters = {
                    @Parameter(name = "keyword",description = "인턴 내용 검색을 위한 keyword 입니다.(필수 입력 x)")
            }
    )
    public List<InternNoticeDTO> getIntern(@RequestParam(required = false) String keyword);
    @Operation(summary = "알람 조회",
            description = "회원의 알람 상태를 조회합니다.",
            parameters = {
                    @Parameter(name = "customUserDetails",description = "회원의 id를 불러오기 위한 인증 정보를 불러옵니다")
            }
    )
    public MemberAlarmSettingDTO findAllByMemberId(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    @Operation(summary = "알람 활성화",
            description = "회원의 알람 상태를 활성화 합니다.",
            parameters = {
                    @Parameter(name = "customUserDetails",description = "회원의 id를 불러오기 위한 인증 정보를 불러옵니다"),
                    @Parameter(name = "keyword",description = "알람의 활성화를 위한 이름으로 keyword를 받아 옵니다.")
            }
    )
    public void updateActive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword);
    @Operation(summary = "알람 비활성화",
            description = "회원의 알람 상태를 비활성화 합니다.",
            parameters = {
                    @Parameter(name = "customUserDetails",description = "회원의 id를 불러오기 위한 인증 정보를 불러옵니다"),
                    @Parameter(name = "keyword",description = "알람의 비활성화를 위한 이름으로 keyword를 받아 옵니다.")
            }
    )
    public void updateInactive(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody Map<String, String> keyword);
    @Operation(summary = "SNS 연결 조회",
            description = "연결된 SNS 이메일을 조회하고 출렵합니다..",
            parameters = {
                    @Parameter(name = "customUserDetails",description = "회원의 phoneNumber를 불러오기 위한 인증 정보를 불러옵니다")
            }
    )
    public List<MemberDTO> find(@AuthenticationPrincipal CustomUserDetails customUserDetails);
}
