package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.CommunityLeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CLRepo extends JpaRepository<CommunityLeader,Long> {
    CommunityLeader findByUserId(Long id);
}
