package com.project.RegisterSystem.service.Event;

import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.ListStudentAppceptDto;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto, HttpServletRequest request);
    EventDto updateEvent(EventDto eventDto, Long eventId);
    ResponseStatusDto deleteEvent(Long id);
    EventDto getEvent(Long id);
    List<EventDto> getAllEvent();

    List<EventDto> getEventByUniversity(HttpServletRequest request);

    ResponseStatusDto acceptEvent(Long id);

    List<ListStudentAppceptDto> getListStudentAppcept(Long id,HttpServletRequest request);
    List<EventDto> getAllEventByCL(HttpServletRequest request);


}
