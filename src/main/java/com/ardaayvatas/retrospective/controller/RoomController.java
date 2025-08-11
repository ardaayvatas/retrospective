package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.request.CreateRoomRequest;
import com.ardaayvatas.retrospective.data.response.RoomResponse;
import com.ardaayvatas.retrospective.service.intf.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Room", description = "Endpoints for room operations like creating and fetching rooms")
public class RoomController {

    private final RoomService roomService;

    @Operation(
            summary = "Create a new room",
            description = "Creates a new room with exactly 3 categories. Returns the room ID and category information."
    )
    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody @Valid CreateRoomRequest request) {
        RoomResponse response = roomService.createRoom(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get room details",
            description = "Fetches room details (including categories) by its UUID."
    )
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable UUID roomId) {
        RoomResponse response = roomService.getRoom(roomId);
        return ResponseEntity.ok(response);
    }
}