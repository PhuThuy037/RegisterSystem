package com.project.RegisterSystem.mapper;

import com.project.RegisterSystem.dto.CommnityLeaderDto;
import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.UniversityDto;
import com.project.RegisterSystem.entity.CommunityLeader;
import com.project.RegisterSystem.entity.Event;
import com.project.RegisterSystem.entity.University;
import com.project.RegisterSystem.enums.Status;
import com.project.RegisterSystem.exception.NotFoundException;
import com.project.RegisterSystem.repository.CLRepo;
import com.project.RegisterSystem.repository.EventRepo;
import com.project.RegisterSystem.repository.UniversityRepo;
import com.project.RegisterSystem.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final UserRepo userRepo;
    private final CLRepo clRepo;
    private final UniversityRepo universityRepo;

    // Chuyển từ Entity Event sang DTO EventDto
    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());  // Thêm id cho sự kiện
        eventDto.setEventName(event.getEventName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setEndTime(event.getEndTime());
        eventDto.setStatus(event.getStatus());
        eventDto.setLocation(event.getLocation());
        eventDto.setNumberOfPeople(event.getNumberOfPeople());





        // Ánh xạ UniversityDto nếu có (1 trường đại học duy nhất)
        if (event.getUniversity() != null) {
            University university = event.getUniversity();
            UniversityDto universityDto = new UniversityDto();
            universityDto.setId(university.getId());
            universityDto.setUniversityName(university.getUniversityName());
            eventDto.setUniversityName(universityDto.getUniversityName());
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
        CommunityLeader communityLeader = clRepo.findById(eventDto.getCommnityLeaderDto().getId())
                .orElseThrow(()-> new NotFoundException("Not Found user"));
       event.setCommunityLeader(communityLeader);



        // Nếu có UniversityDto, ánh xạ sang trường đại học entity
        if (eventDto.getEventName() != null) {
            University university = new University();
            university.setId(eventDto.getId());
            university.setUniversityName(eventDto.getEventName());
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
