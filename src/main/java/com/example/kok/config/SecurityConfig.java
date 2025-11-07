package com.example.kok.config;

import com.example.kok.auth.*;
import com.example.kok.enumeration.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final JwtAuthenticationHandler jwtAuthenticationHandler;
    private final JwtAuthorizationHandler jwtAuthorizationHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/error/**",
                                "/api/**",
                                "/mail/**",
                                "/sms/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/enterprise-console/**",
                                "/enterprise-console/experience/list/**",
                                "/enterprise-console/experience/create/**",
                                "/member/**",
                                "/main/**",
                                "/admin/login",
                                "/experience/**",
                                "/intern/**",
                                "/company/**",
                                "/community/**",
                                "/css/**",
                                "/js/**",
                                "/fonts/**",
                                "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/enterprise-console/**").hasRole(UserRole.COMPANY.name())
                        .anyRequest().authenticated()
                )
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()   // 임시 모든 요청 허용
//                )
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(jwtAuthenticationHandler)
                                .accessDeniedHandler(jwtAuthorizationHandler)
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo->userInfo.userService(oAuth2UserService))
                        .successHandler(oAuth2SuccessHandler)
                        .failureHandler(oAuth2FailureHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}