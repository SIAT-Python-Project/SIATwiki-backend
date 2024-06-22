package com.webserver.siatwiki.cookie.inerceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                    request.getSession().setAttribute("userName", cookie.getValue());
                    request.getSession().setAttribute("userEmail", cookie.getValue());
                    request.getSession().setAttribute("userId", cookie.getValue()); 
                }
            }
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if (request.getRequestURI().equals("/api/logout")) {
            Cookie[] cookies = request.getCookies();
            
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                	System.out.println(cookie.getName());
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    System.out.println(cookie.getName());
                }
            }
            request.getSession().invalidate();
        }
    }
}