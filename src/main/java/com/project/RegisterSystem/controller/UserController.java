package com.project.RegisterSystem.controller;

import com.project.RegisterSystem.dto.RegisterDto;
import com.project.RegisterSystem.dto.UserDto;
import com.project.RegisterSystem.dto.request.LoginRequest;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStatusDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseStatusDto> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok(userService.login(loginRequest,response));
    }
    @GetMapping("/info")
    public ResponseEntity<UserDto> getInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getLoginUser(request));
    }
}
