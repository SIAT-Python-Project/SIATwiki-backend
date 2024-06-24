package com.webserver.siatwiki.common.inerceptor.cookie;

import com.webserver.siatwiki.common.response.error.ErrorResponse;
import com.webserver.siatwiki.common.util.cookie.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webserver.siatwiki.user.dto.UserLoginDTO.UserLoginResponseDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.webserver.siatwiki.common.response.error.ErrorCode.NOT_COOKIE;

public class CookieInterceptor implements HandlerInterceptor {
	@Autowired
	private CookieUtil cookieUtil;

	private static final String LOGIN_PATH = "/api/login";
	private static final String LOGOUT_PATH = "/api/logout";
	private static final String SIGNUP_PATH = "/api/sign-up";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String method = request.getMethod();

		if (method.equals("GET")) {
			return true;
		}

		if (request.getRequestURI().equals(LOGIN_PATH) || request.getRequestURI().equals(LOGOUT_PATH) || request.getRequestURI().equals(SIGNUP_PATH)) {
			return true;
		}

		Cookie[] cookies = request.getCookies();

		if (!cookieUtil.checkCookie(cookies)) {
			response.setContentType("application/json");
			response.setStatus(403);
			String content = ErrorResponse.toString(NOT_COOKIE);
			response.getOutputStream().write(content.getBytes());
			return false;
		}

        return true;
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getRequestURI().equals(LOGIN_PATH)) {
			UserLoginResponseDTO cookieUser = (UserLoginResponseDTO) request.getAttribute("userLoginDTO");

			if (cookieUser != null) {
				Cookie emailCookie = new Cookie("email", cookieUser.getEmail());
				Cookie nameCookie = new Cookie("name", cookieUser.getName());
				Cookie idCookie = new Cookie("id", cookieUtil.encipherCookie(cookieUser.getId()));

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
		if (request.getRequestURI().equals(LOGOUT_PATH)) {
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