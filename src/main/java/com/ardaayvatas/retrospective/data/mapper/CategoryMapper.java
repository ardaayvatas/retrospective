package com.ardaayvatas.retrospective.data.mapper;

import com.ardaayvatas.retrospective.data.dto.CategoryDTO;
import com.ardaayvatas.retrospective.data.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
}