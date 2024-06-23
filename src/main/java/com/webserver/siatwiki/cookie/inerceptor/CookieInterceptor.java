package com.webserver.siatwiki.cookie.inerceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webserver.siatwiki.user.dto.UserLoginDTO.UserLoginResponseDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieInterceptor implements HandlerInterceptor {
	@Autowired
	private Environment env;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getRequestURI().equals("/api/login")) {
			UserLoginResponseDTO cookieUser = (UserLoginResponseDTO) request.getAttribute("userLoginDTO");

			if (cookieUser != null) {
				String front = env.getProperty("cookie.front");
				String back = env.getProperty("cookie.back");
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
					cookie.setMaxAge(0);
					cookie.setValue("");
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			request.getSession().invalidate();
		}
	}
}