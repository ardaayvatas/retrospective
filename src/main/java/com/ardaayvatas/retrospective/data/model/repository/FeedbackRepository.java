package com.ardaayvatas.retrospective.data.model.repository;

import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByRoomIdOrderByCreatedAtAsc(java.util.UUID roomId);
}