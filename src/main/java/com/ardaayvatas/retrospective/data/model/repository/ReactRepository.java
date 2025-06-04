package com.ardaayvatas.retrospective.data.model.repository;

import com.ardaayvatas.retrospective.data.model.entity.React;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReactRepository extends JpaRepository<React, Long> {
    List<React> findByFeedbackId(Long feedbackId);
    boolean existsByFeedbackIdAndUserIdAndEmojiType(Long feedbackId, java.util.UUID userId, String emojiType);
}