package com.project.RegisterSystem.service.University;

import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.University;

import java.util.List;

public interface UniversityService {
    ResponseStatusDto createUniversity(University university);
    ResponseStatusDto deleteUniversity(Long id);
    University getUniversity(Long id);
    List<University> getAllUniversity();
    ResponseStatusDto updateUniversity(University university, Long id);
}
