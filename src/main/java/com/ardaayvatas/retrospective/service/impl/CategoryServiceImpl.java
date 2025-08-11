package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.model.entity.Category;
import com.ardaayvatas.retrospective.data.model.repository.CategoryRepository;
import com.ardaayvatas.retrospective.service.intf.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategoriesByRoomId(UUID roomId) {
        return categoryRepository.findByRoomId(roomId);
    }

    @Override
    public Category getCategoryEntity(Long aLong) {
        return categoryRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}