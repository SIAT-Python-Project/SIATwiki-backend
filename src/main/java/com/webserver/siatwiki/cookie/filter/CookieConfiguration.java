package com.webserver.siatwiki.cookie.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfiguration {
    @Bean
    public FilterRegistrationBean<CookieFilter> cookieFilter(){
        FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CookieFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
