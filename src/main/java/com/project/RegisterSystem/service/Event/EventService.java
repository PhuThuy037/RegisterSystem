package com.project.RegisterSystem.service.Event;

import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.ListStudentAppceptDto;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);
    EventDto updateEvent(EventDto eventDto, Long eventId);
    ResponseStatusDto deleteEvent(Long id);
    EventDto getEvent(Long id);
    List<EventDto> getAllEvent();

    List<EventDto> getEventByUniversity(String universityName);

    ResponseStatusDto acceptEvent(Long id);

    List<ListStudentAppceptDto> getListStudentAppcept(Long id);


}
