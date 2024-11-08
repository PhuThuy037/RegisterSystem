package com.project.RegisterSystem.service.Event;

import com.project.RegisterSystem.config.JwtUtils;
import com.project.RegisterSystem.dto.CommnityLeaderDto;
import com.project.RegisterSystem.dto.EventDto;
import com.project.RegisterSystem.dto.ListStudentAppceptDto;
import com.project.RegisterSystem.dto.UserDto;
import com.project.RegisterSystem.dto.response.ResponseStatusDto;
import com.project.RegisterSystem.entity.*;
import com.project.RegisterSystem.enums.Status;
import com.project.RegisterSystem.enums.UserRole;
import com.project.RegisterSystem.exception.InvalidCredentialsException;
import com.project.RegisterSystem.exception.NotFoundException;
import com.project.RegisterSystem.mapper.EventMapper;
import com.project.RegisterSystem.repository.*;
import com.project.RegisterSystem.service.Cookie.CookieService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final CookieService cookieService;
    private final JwtUtils jwtUtils;
    private final StudentRepo studentRepo;
    private final UserRepo userRepo;
    private final CLRepo clRepo;
    @Override
    public EventDto createEvent(EventDto eventDto, HttpServletRequest request) {
        // Tìm trường đại học dựa trên tên
        University university = universityRepository.findByUniversityName(eventDto.getUniversityName())
                .orElseThrow(() -> new NotFoundException("University not found"));


        String token =  cookieService.getCookie(request, "token");
        if (token == null) {
            throw new InvalidCredentialsException("Not Authenticated");
        }
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        CommunityLeader communityLeader = clRepo.findByUserId(user.getId());
        CommnityLeaderDto commnityLeaderDto = new CommnityLeaderDto();
        commnityLeaderDto.setName(user.getFullName());
        commnityLeaderDto.setId(communityLeader.getId());
        eventDto.setCommnityLeaderDto(commnityLeaderDto);




        // Chuyển đổi từ DTO sang Entity
        Event event = eventMapper.toEntity(eventDto);


        // Gắn trường đại học vào sự kiện
        event.setUniversity(university);

        // Lưu sự kiện vào cơ sở dữ liệu
        event = eventRepository.save(event);

        communityLeader.getEvent().add(event);

        // Trả về DTO sau khi lưu
        return eventMapper.toDto(event);
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
    public List<EventDto> getEventByUniversity(HttpServletRequest request) {
        // Lấy token từ cookie
        String token =  cookieService.getCookie(request, "token");
        if (token == null) {
            throw new InvalidCredentialsException("Not Authenticated");
        }
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepo.findByUserId(user.getId());

        List<Event> events = eventRepository.findByUniversity_UniversityName(student.getUniversity().getUniversityName())
                .stream()
                .filter(event -> event.getStatus() == Status.Appcept)
                .toList();


        // Chuyển đổi từ Entity Event sang DTO EventDto
        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public ResponseStatusDto acceptEvent(Long id) {
       Event event = eventRepository.findById(id).orElseThrow(()-> new NotFoundException("Event not found"));
        event.setStatus(Status.Appcept);
        eventRepository.save(event);

        return ResponseStatusDto.builder()
                .status(200)
                .message("Event accepted")
                .build();
    }

    @Override
    public List<ListStudentAppceptDto> getListStudentAppcept(Long eventId,HttpServletRequest request) {
        List<Appcept> appcepts = appceptRepository.findByEventId(eventId);
        String token =  cookieService.getCookie(request, "token");
        if (token == null) {
            throw new InvalidCredentialsException("Not Authenticated");
        }
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepo.findByUserId(user.getId());

        return appcepts.stream()
                .map(appcept -> {
                    ListStudentAppceptDto dto = new ListStudentAppceptDto();
                    // Lấy thông tin sinh viên từ Appcept
                    dto.setStudentId(student.getId().toString());
                    dto.setMSSV(student.getMSSV()); // Giả sử bạn có trường fullName trong Student
                    dto.setEventId(eventId.toString());
                    dto.setEventName(appcept.getEvent().getEventName()); // Lấy tên sự kiện từ Appcept
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getAllEventByCL(HttpServletRequest request) {


        String token =  cookieService.getCookie(request, "token");
        if (token == null) {
            throw new InvalidCredentialsException("Not Authenticated");
        }
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        CommunityLeader communityLeader = clRepo.findByUserId(user.getId());
        List<Event> events = communityLeader.getEvent();
        if (events.isEmpty()) {
            return List.of(); // Trả về danh sách rỗng nếu không có sự kiện
        }

        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }
}
