package com.project.RegisterSystem.service.User;

import com.project.RegisterSystem.dto.RegisterDto;
import com.project.RegisterSystem.dto.response.LoginRequest;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    User getLoginUser();
    ResponseStatusDto register(RegisterDto registerDto);
    ResponseStatusDto login(LoginRequest loginRequest, HttpServletResponse response);

}
