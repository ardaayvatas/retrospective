package com.ardaayvatas.retrospective.data.dto;

import java.util.List;

public record CategoryFeedbackExportDTO(
        String categoryName,
        List<FeedbackWithReactionsExportDTO> feedbacks
) {}