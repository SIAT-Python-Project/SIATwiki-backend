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

    public String encipherCookie(final String id) {
        return front + id + back;
    }

    public int decipherCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                String value = cookie.getValue();
                String userId = value.replace(front, "").replace(back, "");

                try {
                    return Integer.parseInt(userId);
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }

        return -1;
    }

    public boolean checkCookie(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }

        return decipherCookie(cookies) != -1;
    }
}
