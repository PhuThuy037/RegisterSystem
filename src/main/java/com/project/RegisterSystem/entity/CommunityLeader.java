package com.project.RegisterSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "community_leader")
public class CommunityLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String phone;

    private String address;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
