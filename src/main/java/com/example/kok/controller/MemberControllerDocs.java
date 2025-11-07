package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.FileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name="Member", description = "Member Rest API")
public interface MemberControllerDocs {
    @Operation(summary = "보관함에 파일 저장",
            description = "파일을 보관함에 저장함",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 회원의 정보 조회"),
                @Parameter(name="files", description = "보관함에 넣을 파일들")
            }
    )
    public void saveStorageFile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                @RequestParam(value="files") List<MultipartFile> files);

    @Operation(summary = "보관함 파일 불러오기",
            description = "보관함에 저장된 파일 목록을 불러옴",
            parameters = {
                @Parameter(name = "customUserDetails", description = "로그인한 회원의 정보 조회")
            }
    )
    public List<FileDTO> loadStorageFile(@AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "보관함 파일 삭제",
            description = "선택된 파일을 보관함에서 삭제",
            parameters = {
                @Parameter(name = "fileId", description = "선택된 파일의 id")
            }
    )
    public ResponseEntity<?> deleteSorageFile(@RequestParam(value="fileId") Long fileId);
}
