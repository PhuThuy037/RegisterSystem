package com.project.RegisterSystem.entity;

import com.project.RegisterSystem.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is require")
    private String fullName;

    @Email
    @NotBlank(message = "email is require")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "password is require")
    @Size(min = 6 , message = "At least 6 character")
    private String password;

    private String phoneNumber;

    private UserRole role;


}
