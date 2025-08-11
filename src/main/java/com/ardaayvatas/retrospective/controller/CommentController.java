package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.request.CreateCommentRequest;
import com.ardaayvatas.retrospective.data.response.CommentResponse;
import com.ardaayvatas.retrospective.service.intf.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Endpoints for adding and retrieving comments under feedback")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Add comment to a feedback",
            description = "Adds a comment to the given feedback. The comment is linked to both the feedback and the user."
    )
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid CreateCommentRequest request) {
        CommentResponse response = commentService.createComment(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get comments under a feedback",
            description = "Returns all comments that have been added under the specified feedback."
    )
    @GetMapping("/by-feedback/{feedbackId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByFeedbackId(
            @PathVariable @Parameter(description = "Feedback ID to fetch comments for") Long feedbackId) {
        List<CommentResponse> comments = commentService.getCommentsByFeedbackId(feedbackId);
        return ResponseEntity.ok(comments);
    }
}