package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepo extends JpaRepository<University, Long> {
}
