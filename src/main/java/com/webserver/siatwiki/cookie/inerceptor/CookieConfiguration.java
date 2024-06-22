package com.webserver.siatwiki.cookie.inerceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CookieConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor())
                .addPathPatterns("/api/login") // 로그인 요청에 대해서만 인터셉터 적용
                .addPathPatterns("/api/logout"); // 로그아웃 요청에 대해서만 인터셉터 적용
    }
    
    @Bean
    public CookieInterceptor cookieInterceptor() {
        return new CookieInterceptor();
    }
}