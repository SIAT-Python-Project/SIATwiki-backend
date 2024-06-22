//package com.webserver.siatwiki.cookie.inerceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CookieConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(cookieInterceptor())
//                .addPathPatterns("/api/*"); // 적용할 URL 패턴 설정
//    }
//​
//    @Bean
//    public CookieInterceptor cookieInterceptor() {
//        return new CookieInterceptor();
//    }
//}
