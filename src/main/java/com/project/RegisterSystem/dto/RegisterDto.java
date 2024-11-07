package com.project.RegisterSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.RegisterSystem.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterDto {

    // Thông tin chung cho tất cả các loại người dùng
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    private String phoneNumber;

    private UserRole role; // Vai trò của người dùng (Admin, Student, Community Leader, University Staff)

    // Thông tin riêng biệt cho các loại người dùng
    private String address; // Địa chỉ (có thể dùng cho cả sinh viên và lãnh đạo cộng đồng)

    private String universityName; // ID của trường (dành cho Sinh viên và Nhân viên trường)

    private Long eventId; // ID của sự kiện (dành cho Lãnh đạo cộng đồng)
}
