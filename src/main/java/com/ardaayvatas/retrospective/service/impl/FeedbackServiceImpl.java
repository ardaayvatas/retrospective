package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.dto.FeedbackDTO;
import com.ardaayvatas.retrospective.data.mapper.FeedbackMapper;
import com.ardaayvatas.retrospective.data.model.entity.Category;
import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import com.ardaayvatas.retrospective.data.model.entity.Room;
import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.model.repository.FeedbackRepository;
import com.ardaayvatas.retrospective.data.request.CreateFeedbackRequest;
import com.ardaayvatas.retrospective.data.response.FeedbackResponse;
import com.ardaayvatas.retrospective.service.intf.CategoryService;
import com.ardaayvatas.retrospective.service.intf.FeedbackService;
import com.ardaayvatas.retrospective.service.intf.RoomService;
import com.ardaayvatas.retrospective.service.intf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    private final UserService userService;
    private final CategoryService categoryService;
    private final RoomService roomService;

    @Override
    public FeedbackResponse createFeedback(CreateFeedbackRequest request, UUID roomId) {
        User user = userService.getUserEntity(request.userId());
        Category category = categoryService.getCategoryEntity(request.categoryId());
        Room room = roomService.getRoomEntity(roomId);

        Feedback feedback = Feedback.builder()
                .content(request.content())
                .isAnonymous(request.isAnonymous())
                .room(room)
                .user(user)
                .category(category)
                .build();

        Feedback saved = feedbackRepository.save(feedback);
        return feedbackMapper.toResponse(feedbackMapper.toDTO(saved));
    }

    @Override
    public List<FeedbackResponse> getFeedbacksByRoomId(UUID roomId) {
        return feedbackRepository.findByRoomIdOrderByCreatedAtAsc(roomId).stream()
                .map(feedbackMapper::toDTO)
                .map(feedbackMapper::toResponse)
                .toList();
    }

    @Override
    public Feedback getFeedbackEntity(Long aLong) {
        return feedbackRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));
    }

    @Override
    public FeedbackDTO saveFeedbackFromSocket(FeedbackDTO dto) {
        Feedback entity = feedbackMapper.toEntity(dto);
        Feedback saved = feedbackRepository.save(entity);
        return feedbackMapper.toDTO(saved);
    }
}