package com.example.kok.controller;

import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// http://localhost:10000/swagger-ui/index.html
@Tag(name = "SideBarSupport", description = "SideBarSupport API")
public interface SideBarSupportControllerDocs {
    @Operation(summary = "사이드 바 - 고객지원 목록",
            description = "사이드 바의 고객지원 목록을 볼 수 있다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)")
            }
    )
    public ResponseEntity<AdminNoticeCriteriaDTO> goToSupportSideBar (@PathVariable int page);

    @Operation(summary = "사이드 바 - 고객지원 상세",
            description = "사이드 바의 고객지원 상세 내용을 볼 수 있다.",
            parameters = {
                @Parameter(name = "id", description = "상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminNoticeDTO> goToSupportSideBarDetail (@PathVariable Long id);
}
