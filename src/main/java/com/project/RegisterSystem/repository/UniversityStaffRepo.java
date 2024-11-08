package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.UniversityStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityStaffRepo extends JpaRepository<UniversityStaff, Long> {
    UniversityStaff findByUserId(Long id);
}
