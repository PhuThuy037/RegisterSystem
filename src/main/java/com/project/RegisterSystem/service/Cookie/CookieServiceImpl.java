package com.project.RegisterSystem.service.Cookie;

import jakarta.servlet.http.HttpServletRequest;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/"); // Đặt đường dẫn cho cookie
        response.addCookie(cookie);
    }

    @Override
    public String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0); // Đặt thời gian sống về 0 để xóa
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
