package com.ardaayvatas.retrospective.data.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "reacts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"feedback_id", "user_id", "emojiType"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class React {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emojiType; // ex: "👍", "😂", "🔥"

    @ManyToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

