package com.project.RegisterSystem.service.User;

import com.project.RegisterSystem.dto.RegisterDto;
import com.project.RegisterSystem.dto.UserDto;
import com.project.RegisterSystem.dto.request.LoginRequest;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    UserDto getLoginUser(HttpServletRequest request);
    ResponseStatusDto register(RegisterDto registerDto);
    ResponseStatusDto login(LoginRequest loginRequest, HttpServletResponse response);

}
