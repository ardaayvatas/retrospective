package com.ardaayvatas.retrospective.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

public record CreateReactRequest(
        @NotBlank(message = "Emoji type is required")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Emoji type must be alphanumeric or underscore")
        String emojiType,
        @NotNull
        Long feedbackId,
        @NotNull
        UUID userId
) {}