package com.ardaayvatas.retrospective.data.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // ex: "mad", "sad", "glad"

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}