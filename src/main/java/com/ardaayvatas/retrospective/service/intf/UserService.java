package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.request.CreateUserRequest;
import com.ardaayvatas.retrospective.data.response.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(CreateUserRequest request, UUID roomId);
    List<UserResponse> getUsersByRoomId(UUID roomId);
    User getUserEntity(UUID uuid);
}
