package com.project.RegisterSystem.repository;

import com.project.RegisterSystem.entity.Appcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppceptRepo extends JpaRepository<Appcept, Long> {
    // Lấy tất cả các Appcept liên quan đến Event ID (để lấy danh sách sinh viên đã đăng ký)
    List<Appcept> findByEventId(Long eventId);
}
