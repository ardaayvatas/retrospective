package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.mapper.UserMapper;
import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.model.repository.UserRepository;
import com.ardaayvatas.retrospective.data.request.CreateUserRequest;
import com.ardaayvatas.retrospective.data.response.UserResponse;
import com.ardaayvatas.retrospective.service.intf.RoomService;
import com.ardaayvatas.retrospective.service.intf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoomService roomService;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request, UUID roomId) {
        if (userRepository.existsByNicknameAndRoomId(request.nickname(), roomId)) {
            throw new IllegalArgumentException("Nickname already taken in this room.");
        }

        Room room = roomService.getRoomEntity(roomId); // özel method entity dönsün diye

        User user = User.builder()
                .nickname(request.nickname())
                .room(room)
                .build();

        User saved = userRepository.save(user);
        return userMapper.toResponse(userMapper.toDTO(saved));
    }

    @Override
    public List<UserResponse> getUsersByRoomId(UUID roomId) {
        return userRepository.findByRoomId(roomId).stream()
                .map(userMapper::toDTO)
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public User getUserEntity(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}