package com.ardaayvatas.retrospective.data.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        String content,
        LocalDateTime createdAt,
        UserDTO user
) {}
