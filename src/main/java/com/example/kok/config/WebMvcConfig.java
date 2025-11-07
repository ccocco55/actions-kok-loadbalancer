package com.example.kok.config;


import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.interceptor.Interceptor;
import com.example.kok.service.MainpageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;
    private final MainpageService mainpageService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor(jwtTokenProvider,mainpageService))
                .addPathPatterns("/**");
//                .excludePathPatterns("/test/**");
    }
}