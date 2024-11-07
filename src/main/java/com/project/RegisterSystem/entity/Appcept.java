package com.project.RegisterSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "accept_list")
@Data
public class Appcept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Student> studentList;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
