package com.ardaayvatas.retrospective.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateCommentRequest(
        @NotBlank(message = "Content is required")
        @Size(min = 1, max = 300, message = "Comment must be between 1 and 300 characters")
        String content,
        @NotNull
        Long feedbackId,
        @NotNull
        UUID userId
) {}