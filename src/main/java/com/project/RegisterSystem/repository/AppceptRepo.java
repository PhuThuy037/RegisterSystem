package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.Appcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppceptRepo extends JpaRepository<Appcept, Long> {
}
