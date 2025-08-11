package com.ardaayvatas.retrospective.data.dto;

import java.time.LocalDateTime;

public record FeedbackDTO(
        Long id,
        String content,
        boolean isAnonymous,
        LocalDateTime createdAt,
        UserDTO user,
        CategoryDTO category
) {}
