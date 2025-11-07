package com.example.kok.controller;

import com.example.kok.dto.AdminCompanyCriteriaDTO;
import com.example.kok.dto.AdminCompanyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

// http://localhost:10000/swagger-ui/index.html
@Tag(name = "AdminCompany", description = "AdminCompanies API")
public interface AdminCompaniesApiControllerDocs {

    @Operation(summary = "1페이지 당 기업 10개씩 끊어 불러오기 ",
            description = "받은 페이지와 키워드에 맞는 기업을 검색해 보여줍니다.",
            parameters = {
                    @Parameter(name = "page", description = "현재 페이지"),
                    @Parameter(name = "keyword", description = "검색한 키워드")
            }
    )
    public ResponseEntity<?> findUserMembers(@PathVariable("page") int page, @RequestParam(required = false) String keyword);

    @Operation(summary = "기업의 상세 보기",
            description = "기업의 정보, 체험공고/인턴 모집 공고를 최근 3개까지 보여준다.",
            parameters = {
                    @Parameter(name = "userId", description = "기업의 아이디")
            }
    )
    public ResponseEntity<?> findUserMemberById(@PathVariable("userId") Long userId);

}
