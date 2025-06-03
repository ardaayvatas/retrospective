package com.ardaayvatas.retrospective.data.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}