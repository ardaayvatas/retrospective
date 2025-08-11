package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.dto.FeedbackDTO;
import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import com.ardaayvatas.retrospective.data.response.FeedbackResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface FeedbackMapper {
    FeedbackDTO toDTO(Feedback feedback);
    Feedback toEntity(FeedbackDTO dto);
    FeedbackResponse toResponse(FeedbackDTO dto);
}
