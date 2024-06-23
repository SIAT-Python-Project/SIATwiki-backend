	package com.webserver.siatwiki.cookie.inerceptor;
	
	import org.springframework.web.servlet.HandlerInterceptor;
	import org.springframework.web.servlet.ModelAndView;
	
	import com.webserver.siatwiki.user.dto.UserLoginDTO.UserLoginResponseDTO;
	
	import jakarta.servlet.http.Cookie;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	
	public class CookieInterceptor implements HandlerInterceptor {
	
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        return true;  
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        if (request.getRequestURI().equals("/api/login")) {
	            UserLoginResponseDTO cookieUser = (UserLoginResponseDTO) request.getAttribute("userLoginDTO");
	            System.out.println(cookieUser.getId());  
	            if (cookieUser != null) {
	                String front = "lvbkj1z59521d5gan5y9i7i73j";
	                String back = "ppqkdkvn53008521g";
	                Cookie emailCookie = new Cookie("email", cookieUser.getEmail());
	                Cookie nameCookie = new Cookie("name", cookieUser.getName());
	                Cookie idCookie = new Cookie("id", front + cookieUser.getId() + back);

	                emailCookie.setPath("/");
	                nameCookie.setPath("/");
	                idCookie.setPath("/");

	                response.addCookie(emailCookie);
	                response.addCookie(nameCookie);
	                response.addCookie(idCookie);
	            }
	        }
	    }
	
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
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