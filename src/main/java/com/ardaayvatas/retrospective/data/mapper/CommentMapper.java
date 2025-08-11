package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.dto.CommentDTO;
import com.ardaayvatas.retrospective.data.model.entity.Comment;
import com.ardaayvatas.retrospective.data.response.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);
    Comment toEntity(CommentDTO dto);
    CommentResponse toResponse(CommentDTO dto);
}