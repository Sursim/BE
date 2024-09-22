package com.pnu.sursim.global.auth.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static String Authorization = "Authorization";

    public static Cookie createCookie(String value) {

        Cookie cookie = new Cookie(Authorization, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
