package com.ardaayvatas.retrospective.data.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record RoomExportSummaryDTO(
        String roomId,
        LocalDateTime createdAt,
        LocalDateTime expireAt,
        List<String> participants,
        List<String> categories,
        Map<String, Integer> feedbackCountPerCategory,
        List<CategoryFeedbackExportDTO> feedbacksByCategory
) {}