package com.ardaayvatas.retrospective.data.dto;

import java.util.List;
import java.util.Map;

public record FeedbackWithReactionsExportDTO(
        String content,
        Map<String, Integer> reactions,
        List<String> comments
) {}