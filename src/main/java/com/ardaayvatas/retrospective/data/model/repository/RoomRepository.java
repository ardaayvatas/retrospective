package com.ardaayvatas.retrospective.data.model.repository;

import com.ardaayvatas.retrospective.data.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}