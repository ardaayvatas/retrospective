package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.dto.UserDTO;
import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
    UserResponse toResponse(UserDTO dto);
}
