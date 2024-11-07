package com.project.RegisterSystem.controller;

import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.University;
import com.project.RegisterSystem.service.University.UniversityImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityImpl universityService;
    @PostMapping("/create")
    public ResponseEntity<ResponseStatusDto> createUniversity(@Valid @RequestBody University university) {
        return ResponseEntity.ok(universityService.createUniversity(university));
    }
    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.getUniversity(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<University>> getAllUniversity() {
        return ResponseEntity.ok(universityService.getAllUniversity());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStatusDto> deleteUniversity(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.deleteUniversity(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<ResponseStatusDto> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        return ResponseEntity.ok(universityService.updateUniversity(university,id));
    }
}
