package com.ardaayvatas.retrospective.service.intf;

import java.util.UUID;

public interface RoomExportService {
    byte[] exportRoomAsPdf(UUID roomId);
}
