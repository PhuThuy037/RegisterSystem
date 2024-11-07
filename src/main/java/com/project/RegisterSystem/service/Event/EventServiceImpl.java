package com.project.RegisterSystem.service.Event;

import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.ListStudentAppceptDto;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.Appcept;
import com.project.RegisterSystem.entity.Event;
import com.project.RegisterSystem.entity.Student;
import com.project.RegisterSystem.entity.University;
import com.project.RegisterSystem.enums.Status;
import com.project.RegisterSystem.exception.NotFoundException;
import com.project.RegisterSystem.mapper.EventMapper;
import com.project.RegisterSystem.repository.AppceptRepo;
import com.project.RegisterSystem.repository.EventRepo;
import com.project.RegisterSystem.repository.StudentRepo;
import com.project.RegisterSystem.repository.UniversityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepository;
    private final UniversityRepo universityRepository;
    private final EventMapper eventMapper;
    private final AppceptRepo appceptRepository;
    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);

        // Nếu có trường đại học, tìm và gán cho event
        if (eventDto.getUniversities() != null) {
            Optional<University> universityOptional = universityRepository.findById(eventDto.getUniversities().getId());
            if (universityOptional.isPresent()) {
                event.setUniversity(universityOptional.get());
            } else {
                throw new NotFoundException("University not found");
            }
        }

        // Lưu event vào cơ sở dữ liệu
        Event savedEvent = eventRepository.save(event);

        // Chuyển lại từ Entity sang DTO và trả về
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto, Long eventId) {
        // Kiểm tra sự tồn tại của event trước khi cập nhật
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            throw new NotFoundException("Event not found");
        }

        // Chuyển từ DTO sang Entity
        Event event = eventMapper.toEntity(eventDto);

        // Cập nhật event
        Event updatedEvent = eventRepository.save(event);

        // Chuyển lại từ Entity sang DTO và trả về
        return eventMapper.toDto(updatedEvent);

    }

    @Override
    public ResponseStatusDto deleteEvent(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new RuntimeException("Event not found");
        }

        // Xóa event
        eventRepository.deleteById(id);

        return ResponseStatusDto.builder()
                .status(200)
                .message("Event deleted successfully")
                .build();
    }

    @Override
    public EventDto getEvent(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new RuntimeException("Event not found");
        }

        // Chuyển từ Entity sang DTO và trả về
        return eventMapper.toDto(eventOptional.get());
    }

    @Override
    public List<EventDto> getAllEvent() {
        List<Event> events = eventRepository.findAll();

        // Chuyển danh sách từ Entity sang DTO
        return eventMapper.toDtoList(events);
    }

    @Override
    public List<EventDto> getEventByUniversity(String universityName) {
        // Tìm sự kiện liên quan đến trường đại học
        List<Event> events = eventRepository.findByUniversity_UniversityName(universityName);

        // Chuyển đổi từ Entity Event sang DTO EventDto
        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public ResponseStatusDto acceptEvent(Long id) {
       Event event = eventRepository.findById(id).orElseThrow(()-> new NotFoundException("Event not found"));

        event.setStatus(Status.Appcept);
        return ResponseStatusDto.builder()
                .status(200)
                .message("Event accepted")
                .build();
    }

    @Override
    public List<ListStudentAppceptDto> getListStudentAppcept(Long eventId) {
        List<Appcept> appcepts = appceptRepository.findByEventId(eventId);
        return appcepts.stream()
                .map(appcept -> {
                    ListStudentAppceptDto dto = new ListStudentAppceptDto();
                    // Lấy thông tin sinh viên từ Appcept
                    Student student = appcept.getStudent();
                    dto.setStudentId(student.getId().toString());
                    dto.setMSSV(student.getMSSV()); // Giả sử bạn có trường fullName trong Student
                    dto.setEventId(eventId.toString());
                    dto.setEventName(appcept.getEvent().getEventName()); // Lấy tên sự kiện từ Appcept
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
