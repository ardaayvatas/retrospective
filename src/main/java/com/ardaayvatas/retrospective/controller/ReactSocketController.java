package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.dto.ReactDTO;
import com.ardaayvatas.retrospective.service.intf.ReactService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReactSocketController {

    private final ReactService reactService;

    @MessageMapping("/feedback/{feedbackId}/reaction")
    @SendTo("/topic/feedback/{feedbackId}/reactions")
    public ReactDTO handleReaction(ReactDTO reactionDTO) {
        return reactService.saveReactFromSocket(reactionDTO);
    }
}