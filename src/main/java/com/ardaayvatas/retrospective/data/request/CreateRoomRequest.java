package com.ardaayvatas.retrospective.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CreateRoomRequest(
        @NotNull(message = "Category list is required")
        @Size(min = 3, max = 3, message = "Exactly 3 categories must be provided")
        List<@NotBlank(message = "Category name must not be blank")
        @Size(min = 1, max = 30, message = "Category name must be between 1 and 30 characters")
                String> categoryNames
) {}