package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.Event;
import com.project.RegisterSystem.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByUniversity_UniversityName(String universityName);
}
