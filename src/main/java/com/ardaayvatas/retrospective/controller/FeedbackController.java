package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.request.CreateFeedbackRequest;
import com.ardaayvatas.retrospective.data.response.FeedbackResponse;
import com.ardaayvatas.retrospective.service.intf.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "Endpoints for submitting and retrieving feedbacks in a room")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(
            summary = "Submit feedback to a room",
            description = "Creates a feedback entry in the given room with selected category. Can be anonymous or not."
    )
    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(
            @RequestParam @Parameter(description = "Room ID to post feedback in") UUID roomId,
            @RequestBody @Valid CreateFeedbackRequest request) {
        FeedbackResponse response = feedbackService.createFeedback(request, roomId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get feedbacks for a room",
            description = "Fetches all feedback entries submitted to the specified room."
    )
    @GetMapping("/by-room/{roomId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbacksByRoomId(
            @PathVariable @Parameter(description = "Room ID to retrieve feedbacks from") UUID roomId) {
        List<FeedbackResponse> feedbacks = feedbackService.getFeedbacksByRoomId(roomId);
        return ResponseEntity.ok(feedbacks);
    }
}