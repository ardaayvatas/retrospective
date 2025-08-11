package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.dto.*;
import com.ardaayvatas.retrospective.data.model.entity.*;
import com.ardaayvatas.retrospective.data.model.repository.RoomRepository;
import com.ardaayvatas.retrospective.service.intf.RoomExportService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomExportServiceImpl implements RoomExportService {

    private final RoomRepository roomRepository;

    private RoomExportSummaryDTO fetchRoomExportData(UUID roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        java.util.List<String> participants = room.getUsers().stream()
                .map(User::getNickname)
                .toList();

        java.util.List<String> categoryNames = room.getCategories().stream()
                .map(Category::getName)
                .toList();

        // Tüm feedback'leri room'dan al
        java.util.List<Feedback> allFeedbacks = room.getCategories().stream()
                .flatMap(cat -> cat.getFeedbacks().stream())
                .toList();

        // Kategori bazlı gruplama
        Map<String, java.util.List<Feedback>> feedbacksByCategory = allFeedbacks.stream()
                .collect(Collectors.groupingBy(f -> f.getCategory().getName()));

        // Kategoriye göre sayılar
        Map<String, Integer> feedbackCountPerCategory = feedbacksByCategory.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        java.util.List<CategoryFeedbackExportDTO> categoryDTOs = feedbacksByCategory.entrySet().stream()
                .map(entry -> {
                    String categoryName = entry.getKey();
                    java.util.List<FeedbackWithReactionsExportDTO> feedbackDTOs = entry.getValue().stream()
                            .map(f -> {
                                // Emoji sayıları
                                Map<String, Integer> reactions = f.getReacts().stream()
                                        .collect(Collectors.groupingBy(
                                                React::getEmojiType,
                                                Collectors.reducing(0, e -> 1, Integer::sum)
                                        ));

                                // Sadece yorum içerikleri
                                List<String> comments = f.getComments().stream()
                                        .map(Comment::getContent)
                                        .toList();

                                return new FeedbackWithReactionsExportDTO(
                                        f.getContent(),
                                        reactions,
                                        comments
                                );
                            })
                            .toList();

                    return new CategoryFeedbackExportDTO(categoryName, feedbackDTOs);
                })
                .toList();

        return new RoomExportSummaryDTO(
                room.getId().toString(),
                room.getCreatedAt(),
                room.getExpiresAt(),
                participants,
                categoryNames,
                feedbackCountPerCategory,
                categoryDTOs
        );
    }


    @Override
    public byte[] exportRoomAsPdf(UUID roomId) {
        RoomExportSummaryDTO dto = fetchRoomExportData(roomId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Başlık
            Paragraph title = new Paragraph("RETROSPECTIVE EXPORT", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Room Info
            document.add(new Paragraph("Room ID   : " + dto.roomId()));
            document.add(new Paragraph("Created At: " + dto.createdAt()));
            document.add(new Paragraph("Expire At : " + dto.expireAt()));
            document.add(Chunk.NEWLINE);

            // Genel İstatistikler
            document.add(new Paragraph("Total Participants: " + dto.participants().size()));
            document.add(new Paragraph("Participants: " + String.join(", ", dto.participants())));
            document.add(new Paragraph("Categories: " + String.join(", ", dto.categories())));
            document.add(new Paragraph("Feedbacks Per Category:"));
            for (var entry : dto.feedbackCountPerCategory().entrySet()) {
                document.add(new Paragraph("  - " + entry.getKey() + ": " + entry.getValue()));
            }
            document.add(Chunk.NEWLINE);

            // Feedbacks by Category
            for (CategoryFeedbackExportDTO categoryDTO : dto.feedbacksByCategory()) {
                document.add(new Paragraph("-----------------------------------------------------"));
                document.add(new Paragraph("Category: " + categoryDTO.categoryName(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                document.add(new Paragraph("-----------------------------------------------------"));

                for (FeedbackWithReactionsExportDTO feedback : categoryDTO.feedbacks()) {
                    document.add(new Paragraph("Feedback:"));
                    document.add(new Paragraph("  " + feedback.content()));
                    StringBuilder reactions = new StringBuilder("  ");
                    feedback.reactions().forEach((emoji, count) -> reactions.append(emoji).append(" ").append(count).append("   "));
                    document.add(new Paragraph(reactions.toString()));
                    if (!feedback.comments().isEmpty()) {
                        document.add(new Paragraph("  Comments:"));
                        for (String comment : feedback.comments()) {
                            document.add(new Paragraph("    - " + comment));
                        }
                    }
                    document.add(Chunk.NEWLINE);
                }
                document.add(Chunk.NEWLINE);
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF export failed", e);
        }
    }
}
