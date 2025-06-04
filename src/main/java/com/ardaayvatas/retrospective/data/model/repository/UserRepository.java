package com.ardaayvatas.retrospective.data.model.repository;

import com.ardaayvatas.retrospective.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByRoomId(UUID roomId);
    boolean existsByNicknameAndRoomId(String nickname, UUID roomId);
}