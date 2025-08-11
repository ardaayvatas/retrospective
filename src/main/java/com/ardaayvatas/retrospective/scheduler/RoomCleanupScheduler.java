package com.ardaayvatas.retrospective.scheduler;

import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.model.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomCleanupScheduler {

    private final RoomRepository roomRepository;

    @Scheduled(fixedRate = 12 * 60 * 60 * 1000) // 12 saatte bir çalışır
    public void deleteExpiredRooms() {
        List<Room> expiredRooms = roomRepository.findAllByExpiresAtBefore(LocalDateTime.now());

        if (!expiredRooms.isEmpty()) {
            roomRepository.deleteAll(expiredRooms);
            System.out.println("Silinen oda sayısı: " + expiredRooms.size());
        } else {
            System.out.println("Silinecek oda yok.");
        }
    }
}