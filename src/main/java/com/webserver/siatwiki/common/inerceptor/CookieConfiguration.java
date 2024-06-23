package com.webserver.siatwiki.common.inerceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CookieConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor())
                .addPathPatterns("/api/**");
    }
    
    @Bean
    public CookieInterceptor cookieInterceptor() {
        return new CookieInterceptor();
    }
}