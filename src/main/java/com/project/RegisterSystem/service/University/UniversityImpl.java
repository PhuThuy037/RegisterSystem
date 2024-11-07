package com.project.RegisterSystem.service.University;

import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.University;
import com.project.RegisterSystem.exception.NotFoundException;
import com.project.RegisterSystem.repository.UniversityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityImpl implements UniversityService {

    private final UniversityRepo universityRepo;

    @Override
    public ResponseStatusDto createUniversity(University university) {
        universityRepo.save(university);

        return ResponseStatusDto.builder()
                .status(200)
                .message("Create University success")
                .build();

    }

    @Override
    public ResponseStatusDto deleteUniversity(Long id) {
        universityRepo.findById(id).orElseThrow(()-> new NotFoundException("University not found"));
        universityRepo.deleteById(id);
        return ResponseStatusDto.builder()
                .status(200)
                .message("Delete University success")
                .build();
    }

    @Override
    public University getUniversity(Long id) {
        return universityRepo.findById(id).orElseThrow(()-> new NotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversity() {
        return universityRepo.findAll();
    }

    @Override
    public ResponseStatusDto updateUniversity(University university, Long id) {
        University university1 = universityRepo.findById(id).orElseThrow(()-> new NotFoundException("University not found"));
        university1.setUniversityName(university.getUniversityName());
        universityRepo.save(university1);

        return ResponseStatusDto.builder()
                .status(200)
                .message("Update University success")
                .build();

    }

}
