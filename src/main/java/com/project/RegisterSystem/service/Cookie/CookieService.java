package com.project.RegisterSystem.service.Cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

public interface CookieService {
    public void addCookie(HttpServletResponse response, String name, String value, int maxAge);
    public String getCookie(HttpServletRequest request, String name);
    public void deleteCookie(HttpServletResponse response, String name);

}
