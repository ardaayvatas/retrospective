package com.ardaayvatas.retrospective.data.model.repository;

import com.ardaayvatas.retrospective.data.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRoomId(UUID roomId);
}