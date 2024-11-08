package com.project.RegisterSystem.service.User;

import com.project.RegisterSystem.dto.RegisterDto;
import com.project.RegisterSystem.dto.UserDto;
import com.project.RegisterSystem.dto.request.LoginRequest;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {

    UserDto getLoginUser(HttpServletRequest request);
    ResponseStatusDto register(RegisterDto registerDto);
    ResponseStatusDto login(LoginRequest loginRequest, HttpServletResponse response);
    List<User> getAllUser();
    ResponseStatusDto logout(HttpServletResponse response);
}
