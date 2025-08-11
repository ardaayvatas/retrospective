package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.request.CreateReactRequest;
import com.ardaayvatas.retrospective.data.response.ReactResponse;
import com.ardaayvatas.retrospective.service.intf.ReactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reacts")
@RequiredArgsConstructor
@Tag(name = "React", description = "Endpoints for reacting (emoji) to feedbacks and viewing reactions")
public class ReactController {

    private final ReactService reactService;

    @Operation(
            summary = "React to a feedback",
            description = "Adds a react (emoji) to a feedback by a user. Emojis are predefined on the frontend."
    )
    @PostMapping
    public ResponseEntity<ReactResponse> createReact(@RequestBody @Valid CreateReactRequest request) {
        ReactResponse response = reactService.createReact(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get all reacts for a feedback",
            description = "Returns all react entries (with counts per emoji) for the specified feedback."
    )
    @GetMapping("/by-feedback/{feedbackId}")
    public ResponseEntity<List<ReactResponse>> getReactsByFeedbackId(
            @PathVariable @Parameter(description = "Feedback ID to fetch reacts for") Long feedbackId) {
        List<ReactResponse> reacts = reactService.getReactsByFeedbackId(feedbackId);
        return ResponseEntity.ok(reacts);
    }
}