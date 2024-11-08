package com.project.RegisterSystem.controller;

import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.ListStudentAppceptDto;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.service.Event.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    @PostMapping("/create")
    ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto, HttpServletRequest request) {
        System.out.println(eventDto);
        return ResponseEntity.ok(eventService.createEvent(eventDto,request));
    }
    @PutMapping("update/{id}")
    ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.updateEvent(eventDto, id));
    }
    @DeleteMapping("delete/{id}")
    ResponseEntity<ResponseStatusDto> deleteEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }
    @GetMapping("/get/{id}")
    ResponseEntity<EventDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }
    @GetMapping("/all")
    ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvent());
    }
    @GetMapping("/all-by-university")
    ResponseEntity<List<EventDto>> getAllUniversityEvents(HttpServletRequest request) {
        return ResponseEntity.ok(eventService.getEventByUniversity(request));
    }
    @GetMapping("/accept/{id}")
    ResponseEntity<ResponseStatusDto> acceptEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.acceptEvent(id));
    }
    @GetMapping("/accept-list/{id}")
    ResponseEntity<List<ListStudentAppceptDto>> acceptEventList(Long eventId, HttpServletRequest request) {
        return ResponseEntity.ok(eventService.getListStudentAppcept(eventId,request));
    }
    @GetMapping("/all-by-cl")
    ResponseEntity<List<EventDto>> getAllEventsByCl(HttpServletRequest request) {
        return ResponseEntity.ok(eventService.getAllEventByCL(request));
    }
}
