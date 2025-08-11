package com.ardaayvatas.retrospective.controller;

import com.ardaayvatas.retrospective.service.intf.RoomExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/exports")
@RequiredArgsConstructor
@Tag(name = "Room Export", description = "PDF export for retrospective room")
public class RoomExportController {

    private final RoomExportService roomExportService;

    @GetMapping("/{roomId}/pdf")
    @Operation(
            summary = "Export room as PDF",
            description = "Returns a PDF containing retrospective data of the room."
    )
    public ResponseEntity<byte[]> exportRoomAsPdf(@PathVariable UUID roomId) {
        byte[] pdfBytes = roomExportService.exportRoomAsPdf(roomId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "room-" + roomId + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}