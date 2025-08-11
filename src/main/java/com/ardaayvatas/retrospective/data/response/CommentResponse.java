package com.ardaayvatas.retrospective.data.response;

import com.ardaayvatas.retrospective.data.dto.UserDTO;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        UserDTO user
) {}