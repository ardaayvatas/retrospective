package com.ardaayvatas.retrospective.data.response;

import com.ardaayvatas.retrospective.data.dto.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RoomResponse(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime expiresAt,
        List<CategoryDTO> categories
) {}
