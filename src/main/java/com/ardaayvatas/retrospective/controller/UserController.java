package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.data.request.CreateUserRequest;
import com.ardaayvatas.retrospective.data.response.UserResponse;
import com.ardaayvatas.retrospective.service.intf.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints for user operations like joining a room and listing room users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Join a room",
            description = "Adds a user with a nickname to the specified room. Nickname must be unique within the room."
    )
    @PostMapping("/join")
    public ResponseEntity<UserResponse> joinRoom(
            @RequestParam @Parameter(description = "Room ID to join") UUID roomId,
            @RequestBody @Valid CreateUserRequest request) {
        UserResponse response = userService.createUser(request, roomId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get users in a room",
            description = "Returns a list of users who have joined the specified room."
    )
    @GetMapping("/by-room/{roomId}")
    public ResponseEntity<List<UserResponse>> getUsersByRoomId(
            @PathVariable @Parameter(description = "Room ID to fetch users from") UUID roomId) {
        List<UserResponse> users = userService.getUsersByRoomId(roomId);
        return ResponseEntity.ok(users);
    }
}