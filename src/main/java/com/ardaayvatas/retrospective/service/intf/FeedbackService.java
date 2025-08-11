package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.dto.FeedbackDTO;
import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import com.ardaayvatas.retrospective.data.request.CreateFeedbackRequest;
import com.ardaayvatas.retrospective.data.response.FeedbackResponse;
import java.util.List;
import java.util.UUID;

public interface FeedbackService {
    FeedbackResponse createFeedback(CreateFeedbackRequest request, UUID roomId);
    List<FeedbackResponse> getFeedbacksByRoomId(UUID roomId);
    Feedback getFeedbackEntity(Long aLong);
    FeedbackDTO saveFeedbackFromSocket(FeedbackDTO dto);
}