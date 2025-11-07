package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyExperienceNoticeCriteriaDTO;
import com.example.kok.dto.CompanyInternNoticeCriteriaDTO;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Search;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Company", description = "기업 API")
public interface CompanyListControllerDocs {

//    기업 목록
    @Operation(summary = "기업 목록", description = "검색 조건(산업 규모, 기업 규모, 키워드)과 함께 기업 목록을 페이지 단위로 조회합니다. (무한 스크롤 지원)",
            parameters = {
                    @Parameter(name = "page", description = "조회할 페이지 번호"),
                    @Parameter(name = "search", description = "기업 검색 조건")
            }
    )
    public CompaniesCriteriaDTO getCompanyList(@PathVariable("page") int page,
                                               CompanySearch search,
                                               @AuthenticationPrincipal CustomUserDetails customUserDetails);

//    기업 체험 공고 목록
    @Operation(summary = "기업 체험 공고 목록 조회", description = "검색 조건(키워드)과 함께 특정 기업의 체험 공고 목록을 5개씩 페이지 단위로 조회합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "체험 공고를 조회할 기업 ID"),
                    @Parameter(name = "page", description = "조회할 페이지 번호"),
                    @Parameter(name = "search", description = "체험 공고 검색 조건")

            }
    )
    public CompanyExperienceNoticeCriteriaDTO getCompanyExperienceNotices(@PathVariable("companyId") Long companyId,
                                                                          @PathVariable("page") int page,
                                                                          Search search);

//    기업 인턴 공고 목록
    @Operation(summary = "기업 인턴 목록 조회", description = "검색 조건(키워드)과 함께 특정 기업의 인턴 공고 목록을 5개씩 페이지 단위로 조회합니다.",
            parameters = {
                    @Parameter(name = "companyId", description = "인턴 공고를 조회할 기업 ID"),
                    @Parameter(name = "page", description = "조회할 페이지 번호"),
                    @Parameter(name = "search", description = "인턴 공고 검색 조건")
            }
    )
    public CompanyInternNoticeCriteriaDTO getCompanyInternNotices(@PathVariable("companyId") Long companyId,
                                                                  @PathVariable("page") int page,
                                                                  Search search);
}
