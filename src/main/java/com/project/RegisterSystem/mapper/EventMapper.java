package com.project.RegisterSystem.mapper;

import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.UniversityDto;
import com.project.RegisterSystem.entity.CommunityLeader;
import com.project.RegisterSystem.entity.Event;
import com.project.RegisterSystem.entity.University;
import com.project.RegisterSystem.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    // Chuyển từ Entity Event sang DTO EventDto
    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());  // Thêm id cho sự kiện
        eventDto.setEventName(event.getEventName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setEndTime(event.getEndTime());
        eventDto.setStatus(Status.Processing);
        eventDto.setLocation(event.getLocation());
        eventDto.setNumberOfPeople(event.getNumberOfPeople());

        // Ánh xạ CommunityLeader nếu có
        if(event.getCommunityLeader() != null) {
            eventDto.setCommunityLeader(event.getCommunityLeader());
        }


        // Ánh xạ UniversityDto nếu có (1 trường đại học duy nhất)
        if (event.getUniversity() != null) {
            University university = event.getUniversity();
            UniversityDto universityDto = new UniversityDto();
            universityDto.setId(university.getId());
            universityDto.setUniversityName(university.getUniversityName());
            eventDto.setUniversities(universityDto);
        }

        return eventDto;
    }

    // Chuyển từ DTO EventDto sang Entity Event
    public Event toEntity(EventDto eventDto) {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setDescription(eventDto.getDescription());
        event.setStartTime(eventDto.getStartTime());
        event.setEndTime(eventDto.getEndTime());
        event.setStatus(eventDto.getStatus());
        event.setLocation(eventDto.getLocation());
        event.setNumberOfPeople(eventDto.getNumberOfPeople());

         //Nếu có CommunityLeader, ánh xạ từ dto sang entity
        if(eventDto.getCommunityLeader() != null) {
            event.setCommunityLeader(eventDto.getCommunityLeader());
        }


        // Nếu có UniversityDto, ánh xạ sang trường đại học entity
        if (eventDto.getUniversities() != null) {
            University university = new University();
            university.setId(eventDto.getUniversities().getId());
            university.setUniversityName(eventDto.getUniversities().getUniversityName());
            event.setUniversity(university);
        }

        return event;
    }
    // Chuyển danh sách Entity Event sang danh sách DTO EventDto
    public List<EventDto> toDtoList(List<Event> events) {
        return events.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Chuyển danh sách DTO EventDto sang danh sách Entity Event
    public List<Event> toEntityList(List<EventDto> eventDtos) {
        return eventDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
