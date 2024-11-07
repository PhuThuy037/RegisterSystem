package com.project.RegisterSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "univercity name is require")
    private String universityName;

    @OneToMany(mappedBy = "university")
    private List<UniversityStaff> universityStaff;

}
