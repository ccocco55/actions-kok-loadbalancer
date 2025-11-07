package com.example.kok.controller;


import com.example.kok.auth.CustomUserDetails;
import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.service.CustomUserDetailsService;
import com.example.kok.service.MemberService;
import com.example.kok.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/auth/**")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletResponse response;
    private final UserService userService;
    private final RedisTemplate redisTemplate;

//    로그인
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO,HttpServletRequest request ,HttpServletResponse response) {

        try {
            log.info("login 들어옴");
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserEmail(), userDTO.getUserPassword()));
            log.info("자격증명 중");
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            if(!user.getUserRole().getValue().equals(userDTO.getUserRole().getValue())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "로그인 실패"));
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtTokenProvider.createAccessToken(((CustomUserDetails) authentication.getPrincipal()).getUserEmail());
            String refreshToken = jwtTokenProvider.createRefreshToken(((CustomUserDetails) authentication.getPrincipal()).getUserEmail());

            log.info(refreshToken);
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);


            return ResponseEntity.ok(tokens);

        } catch(AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "로그인 실패: " + e.getMessage()));
        }
    }

    @PostMapping("/reset-cookies")
    public void resetCookies(HttpServletRequest req, HttpServletResponse res){
        Cookie[] cookies = req.getCookies();
        log.info("Cookies are {}", cookies);
        if (cookies != null) {
            boolean accessTokenExists = false;
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    accessTokenExists = true;
                    break;
                }
            }
            if (!accessTokenExists) {
                for (Cookie cookie : cookies) {
                    Cookie newCookie = new Cookie(cookie.getName(), null);
                    newCookie.setHttpOnly(true);
                    newCookie.setSecure(false);
                    newCookie.setPath("/");
                    newCookie.setMaxAge(0);
                    res.addCookie(newCookie);
                }
            }
        }

        Set<String> keys = redisTemplate.keys("refresh:*");
        if(!keys.isEmpty()){
            redisTemplate.delete(keys);
        }
    }

//    로그아웃
    @PostMapping("logout")
    public void logout(@CookieValue(value = "accessToken", required = false) String token) {

        log.info("Logout User: {}", token);
        String username = jwtTokenProvider.getUserName(token);
        String provider = (String) jwtTokenProvider.getClaims(token).get("provider");
        log.info(provider);

        if(provider == null){
            jwtTokenProvider.deleteRefreshToken(username);
        }else{
            jwtTokenProvider.deleteRefreshToken(username,provider);
        }

        jwtTokenProvider.addToBlacklist(token);

        Cookie deleteAccessCookie = new Cookie("accessToken", null);
        deleteAccessCookie.setHttpOnly(true);
        deleteAccessCookie.setSecure(false);
        deleteAccessCookie.setPath("/");
        deleteAccessCookie.setMaxAge(0);

        response.addCookie(deleteAccessCookie);

        Cookie deleteRefreshCookie = new Cookie("refreshToken", null);
        deleteRefreshCookie.setHttpOnly(true);
        deleteRefreshCookie.setSecure(false);
        deleteRefreshCookie.setPath("/");
        deleteRefreshCookie.setMaxAge(0);

        response.addCookie(deleteRefreshCookie);

        Cookie memberNameCookie = new Cookie("memberName", null);
        memberNameCookie.setHttpOnly(true);
        memberNameCookie.setSecure(false);
        memberNameCookie.setPath("/");
        memberNameCookie.setMaxAge(0);

        response.addCookie(memberNameCookie);


        Cookie userEmailCookie = new Cookie("userEmail", null);
        userEmailCookie.setHttpOnly(true);
        userEmailCookie.setSecure(false);
        userEmailCookie.setPath("/");
        userEmailCookie.setMaxAge(0);

        response.addCookie(userEmailCookie);

        Cookie providerCookie = new Cookie("provider", null);
        providerCookie.setHttpOnly(true);
        providerCookie.setSecure(false);
        providerCookie.setPath("/");
        providerCookie.setMaxAge(0);

        response.addCookie(providerCookie);

        Cookie profileCookie = new Cookie("profile", null);
        profileCookie.setHttpOnly(true);
        profileCookie.setSecure(false);
        profileCookie.setPath("/");
        profileCookie.setMaxAge(0);

        response.addCookie(profileCookie);
        userService.deleteCache("company");
    }
}












