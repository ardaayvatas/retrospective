package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.request.CreateRoomRequest;
import com.ardaayvatas.retrospective.data.response.RoomResponse;
import java.util.UUID;

public interface RoomService {
    RoomResponse createRoom(CreateRoomRequest createRoomRequest);
    RoomResponse getRoom(UUID roomId);
    Room getRoomEntity(UUID roomId);
}