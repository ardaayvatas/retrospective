package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.model.entity.Category;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category save(Category category);
    List<Category> getCategoriesByRoomId(UUID roomId);
    Category getCategoryEntity(Long aLong);
}

