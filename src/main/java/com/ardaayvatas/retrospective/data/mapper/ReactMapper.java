package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.dto.ReactDTO;
import com.ardaayvatas.retrospective.data.model.entity.React;
import com.ardaayvatas.retrospective.data.response.ReactResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReactMapper {
    ReactDTO toDTO(React react);
    React toEntity(ReactDTO dto);
    ReactResponse toResponse(ReactDTO dto);
}