package com.example.kok.controller;

import com.example.kok.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;

// http://localhost:10000/swagger-ui/index.html
@Tag(name = "Auth", description = "Auth API")
public interface AuthControllerDocs {
    @Operation(summary = "일반 회원 로그인",
            description = "일반 회원이 로그인 할 때 accessToken과 refreshToken을 발급합니다.",
            parameters = {
                @Parameter(name = "userDTO", description = "로그인 화면에서 입력한 인증 정보")
            }
    )
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletRequest request , HttpServletResponse response);

    @Operation(summary = "로그아웃",
            description = "일반 회원과 기업회원과 SNS 회원의 로그인 정보를 모두 제거하고 로그아웃 처리합니다..",
            parameters = {
                    @Parameter(name = "token", description = "쿠키에 있는 accessToken이 들어옵니다.")
            }
    )
    public void logout(@CookieValue(value = "accessToken", required = false) String token);
}
