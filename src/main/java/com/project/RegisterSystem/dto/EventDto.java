package com.project.RegisterSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.RegisterSystem.entity.CommunityLeader;
import com.project.RegisterSystem.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
    private Long id;
    private String eventName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private String location;
    private int numberOfPeople;
    private CommnityLeaderDto commnityLeaderDto;
    private String universityName;



}