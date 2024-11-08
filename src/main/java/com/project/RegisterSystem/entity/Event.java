package com.project.RegisterSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.RegisterSystem.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Status status;

    private String location;
    private int numberOfPeople;


    @ManyToOne
    @JoinColumn(name = "community_leader_id")
    private CommunityLeader communityLeader;

    @OneToOne
    @JoinColumn(name = "accept_id")
    private Appcept appcept;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @JsonIgnore
    private University university;

    @OneToMany
    @JoinColumn(name = "event")
    private List<Student> students;



}
