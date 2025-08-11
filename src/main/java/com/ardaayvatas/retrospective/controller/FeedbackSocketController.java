package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.dto.FeedbackDTO;
import com.ardaayvatas.retrospective.service.intf.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedbackSocketController {

    private final FeedbackService feedbackService;

    @MessageMapping("/room/{roomId}/feedback") // /app/room/{roomId}/feedback
    @SendTo("/topic/room/{roomId}/feedbacks")  // client'lar burayı dinleyecek
    public FeedbackDTO handleFeedback(FeedbackDTO feedbackDTO) {
        return feedbackService.saveFeedbackFromSocket(feedbackDTO);
    }
}
