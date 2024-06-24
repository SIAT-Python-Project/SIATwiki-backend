package com.webserver.siatwiki.common.util.cookie;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    @Value("${cookie.front}")
    private String front;

    @Value("${cookie.back}")
    private String back;

    public String encipherCookie(final Long id) {
        return front + id + back;
    }

    public Long decipherCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                String value = cookie.getValue();
                String userId = value.replace(front, "").replace(back, "");

                try {
                    return Long.parseLong(userId);
                } catch (NumberFormatException e) {
                    return -1L;
                }
            }
        }

        return -1L;
    }

    public boolean checkCookie(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }

        return decipherCookie(cookies) != -1;
    }
}
