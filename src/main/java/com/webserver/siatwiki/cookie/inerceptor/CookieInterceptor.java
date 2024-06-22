//package com.webserver.siatwiki.cookie.inerceptor;
//
//import java.net.URLDecoder;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class CookieInterceptor implements HandlerInterceptor {
//	
//	@Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 로그인 후 사용자 정보가 쿠키에 있는지 확인하여 세션에 저장
//        Cookie[] cookies = request.getCookies();
//        System.out.println("cookie: " + request.getCookies());
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//				request.getSession().setAttribute("userName", URLDecoder.decode(cookie.getValue(), "UTF-8"));
//				request.getSession().setAttribute("userEmail", URLDecoder.decode(cookie.getValue(), "UTF-8"));
//				request.getSession().setAttribute("userId", Long.parseLong(cookie.getValue()));
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // 로그아웃 시 쿠키 삭제
//        if (request.getRequestURI().equals("/api/logout")) {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//            }
//        }
//    }
//}