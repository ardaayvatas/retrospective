package com.ardaayvatas.retrospective.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Nickname is required")
        @Size(min = 3, max = 20, message = "Nickname must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Nickname must contain only letters")
        String nickname
) {}