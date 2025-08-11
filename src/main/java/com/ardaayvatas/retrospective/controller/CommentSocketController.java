package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.dto.CommentDTO;
import com.ardaayvatas.retrospective.service.intf.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentSocketController {

    private final CommentService commentService;

    @MessageMapping("/feedback/{feedbackId}/comment")
    @SendTo("/topic/feedback/{feedbackId}/comments")
    public CommentDTO handleComment(CommentDTO commentDTO) {
        return commentService.saveCommentFromSocket(commentDTO);
    }
}