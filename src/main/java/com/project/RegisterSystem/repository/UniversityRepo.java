package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepo extends JpaRepository<University, Long> {
    Optional<University> findByUniversityName(String universityName);
}
