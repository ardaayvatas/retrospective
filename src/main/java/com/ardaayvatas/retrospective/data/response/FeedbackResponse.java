package com.ardaayvatas.retrospective.data.response;

import com.ardaayvatas.retrospective.data.dto.CategoryDTO;
import com.ardaayvatas.retrospective.data.dto.UserDTO;

import java.time.LocalDateTime;

public record FeedbackResponse(
        Long id,
        String content,
        boolean isAnonymous,
        LocalDateTime createdAt,
        UserDTO user,
        CategoryDTO category
) {}