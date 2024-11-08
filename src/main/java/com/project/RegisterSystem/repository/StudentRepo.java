package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByUserId(Long userId);
}
