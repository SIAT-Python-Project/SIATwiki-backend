package com.webserver.siatwiki.cookie.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/*")
public class CookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
    	
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 로그인 시 쿠키 설정
        if (request.getRequestURI().endsWith("/api/login")) {
            String name = request.getParameter("name"); // 혹은 request body에서 name 추출
            System.out.println(name);
            if (name != null) {
                Cookie cookie = new Cookie("name", name);
//                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        // 로그아웃 시 쿠키 삭제
        if (request.getRequestURI().endsWith("/logout")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("name")) {
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        }

        // 다음 필터 또는 서블릿 호출
        filterChain.doFilter(servletRequest, servletResponse);
    }

}