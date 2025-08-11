package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.mapper.RoomMapper;
import com.ardaayvatas.retrospective.data.model.entity.Category;
import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.model.repository.RoomRepository;
import com.ardaayvatas.retrospective.data.request.CreateRoomRequest;
import com.ardaayvatas.retrospective.data.response.RoomResponse;
import com.ardaayvatas.retrospective.service.intf.CategoryService;
import com.ardaayvatas.retrospective.service.intf.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final CategoryService categoryService;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponse createRoom(CreateRoomRequest request) {
        List<String> categoryNames = request.categoryNames();
        if (categoryNames.size() != 3) {
            throw new IllegalArgumentException("Exactly 3 categories must be provided.");
        }

        // Entity işlemleri sadece burada yapılır
        Room room = Room.builder().build();
        Room savedRoom = roomRepository.save(room);

        categoryNames.forEach(name -> {
            Category category = Category.builder()
                    .name(name)
                    .room(savedRoom)
                    .build();
            categoryService.save(category); // service üzerinden save
        });

        return roomMapper.toResponse(savedRoom);
    }

    @Override
    public RoomResponse getRoom(UUID roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        return roomMapper.toResponse(room);
    }

    @Override
    public Room getRoomEntity(UUID roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }
}