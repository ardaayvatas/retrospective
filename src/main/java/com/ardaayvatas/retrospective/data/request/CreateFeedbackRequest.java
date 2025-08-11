package com.ardaayvatas.retrospective.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateFeedbackRequest(
        @NotBlank(message = "Content is required")
        @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
        String content,
        @NotNull
        boolean isAnonymous,
        @NotNull
        Long categoryId,
        @NotNull
        UUID userId
) {}
