package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.response.RoomResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface RoomMapper {
    RoomResponse toResponse(Room room);
}
