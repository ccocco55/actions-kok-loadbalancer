package com.example.kok.interceptor;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.repository.FileDAO;
import com.example.kok.service.MainpageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final MainpageService mainpageService;


    //    전처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        String provider = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    CustomUserDetails customUserDetails = (CustomUserDetails) jwtTokenProvider.getAuthentication(token).getPrincipal();
                    mainpageService.findProfile(customUserDetails);
                    log.info(customUserDetails.toString());
                    request.setAttribute("userDTO", customUserDetails);
                }
            }
        }
        return true;
    }

//    후처리
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("요청 처리 완료");
    }
}
