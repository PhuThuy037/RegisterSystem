package com.project.RegisterSystem.service.User;

import com.project.RegisterSystem.config.JwtUtils;
import com.project.RegisterSystem.dto.RegisterDto;
import com.project.RegisterSystem.dto.UserDto;
import com.project.RegisterSystem.dto.response.LoginRequest;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.*;
import com.project.RegisterSystem.enums.UserRole;
import com.project.RegisterSystem.exception.InvalidCredentialsException;
import com.project.RegisterSystem.repository.*;
import com.project.RegisterSystem.service.Cookie.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class RegisterServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final UniversityStaffRepo universityStaffRepo;
    private final UniversityRepo universityRepo;
    private final CLRepo ClRepo;
    private final EventRepo eventRepo;

    private final CookieService cookieService;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public User getLoginUser() {
        return null;
    }

    @Override
    public ResponseStatusDto register(RegisterDto registerDto) {
        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setRole(registerDto.getRole());

        // Save user first
        userRepo.save(user);

        if (registerDto.getRole() == UserRole.Student) {
            Student student = new Student();
            student.setUser(user);
            University university = universityRepo.findByUniversityName(registerDto.getUniversityName())
                    .orElseThrow(() -> new RuntimeException("University not found"));
            student.setUniversity(university);
            student.setAddress(registerDto.getAddress());
            studentRepo.save(student);
            university.getStudents().add(student);
            universityRepo.save(university);
        } else if (registerDto.getRole() == UserRole.UAS) {
            UniversityStaff staff = new UniversityStaff();
            staff.setUser(user);
            // Tìm trường đại học theo tên
            University university = universityRepo.findByUniversityName(registerDto.getUniversityName())
                    .orElseThrow(() -> new RuntimeException("University not found"));
            staff.setUniversity(university);
            universityStaffRepo.save(staff);

            // Nếu cần, thêm staff vào danh sách universityStaff của trường
            university.getUniversityStaff().add(staff);
            universityRepo.save(university); // Cập nhật lại trường với danh sách nhân viên mới

        } else if (registerDto.getRole() == UserRole.CL) {
            CommunityLeader leader = new CommunityLeader();
            leader.setUser(user);
            leader.setAddress(registerDto.getAddress());
            leader.setEvent(eventRepo.findById(registerDto.getEventId()).orElseThrow(() -> new RuntimeException("Event not found")));
            ClRepo.save(leader);
        }

        return ResponseStatusDto.builder()
                .status(200)
                .message("User registered successfully")
                .build();
    }

    @Override
    public ResponseStatusDto login(LoginRequest loginRequest, HttpServletResponse response) {
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Password does not match");
        }
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        String token = jwtUtils.generateToken(userDto);
        cookieService.addCookie(response, "JWT_TOKEN", token, 3600); // Đặt thời gian sống cookie là 1 giờ (3600 giây)
        return ResponseStatusDto.builder()
                .status(200)
                .message("User logged in successfully")
                .build();
    }

}