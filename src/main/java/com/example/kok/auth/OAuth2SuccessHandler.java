package com.example.kok.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String provider = oAuth2User.getAttribute("provider");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String profile = oAuth2User.getAttribute("profile");
        boolean isExist = oAuth2User.getAttribute("exist");
        String role = oAuth2User.getAuthorities().stream().findFirst().orElseThrow(IllegalAccessError::new).getAuthority();
        String path = null;

        if(isExist){
            jwtTokenProvider.createAccessToken(email,provider);
            jwtTokenProvider.createRefreshToken(email,provider);

            path = "/experience/list";
        }else{
            Cookie roleCookie = new Cookie("role", role);
            roleCookie.setHttpOnly(true);
            roleCookie.setSecure(false);
            roleCookie.setPath("/");
            roleCookie.setMaxAge(60 * 60);

            response.addCookie(roleCookie);




            path = "/member/join-social?provider=" + provider;
        }
        Cookie providerCookie = new Cookie("provider", provider);
        providerCookie.setHttpOnly(true);
        providerCookie.setSecure(false);
        providerCookie.setPath("/");
        providerCookie.setMaxAge(60 * 60);

        response.addCookie(providerCookie);

        Cookie userEmailCookie = new Cookie("userEmail", email);
        userEmailCookie.setHttpOnly(true);
        userEmailCookie.setSecure(false);
        userEmailCookie.setPath("/");
        userEmailCookie.setMaxAge(60 * 60);

        response.addCookie(userEmailCookie);

        Cookie memberNameCookie = new Cookie("memberName", name);
        memberNameCookie.setHttpOnly(true);
        memberNameCookie.setSecure(false);
        memberNameCookie.setPath("/");
        memberNameCookie.setMaxAge(60 * 60);

        response.addCookie(memberNameCookie);

        Cookie memberProfileUrlCookie = new Cookie("profile", profile);
        memberProfileUrlCookie.setHttpOnly(true);
        memberProfileUrlCookie.setSecure(false);
        memberProfileUrlCookie.setPath("/");
        memberProfileUrlCookie.setMaxAge(60 * 60);

        response.addCookie(memberProfileUrlCookie);

        response.sendRedirect(path);
    }
}

















