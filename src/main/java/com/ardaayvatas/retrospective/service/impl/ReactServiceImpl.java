package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.dto.ReactDTO;
import com.ardaayvatas.retrospective.data.mapper.ReactMapper;
import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import com.ardaayvatas.retrospective.data.model.entity.React;
import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.model.repository.ReactRepository;
import com.ardaayvatas.retrospective.data.request.CreateReactRequest;
import com.ardaayvatas.retrospective.data.response.ReactResponse;
import com.ardaayvatas.retrospective.service.intf.FeedbackService;
import com.ardaayvatas.retrospective.service.intf.ReactService;
import com.ardaayvatas.retrospective.service.intf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactServiceImpl implements ReactService {

    private final ReactRepository reactRepository;
    private final UserService userService;
    private final FeedbackService feedbackService;
    private final ReactMapper reactMapper;

    @Override
    public ReactResponse createReact(CreateReactRequest request) {
        User user = userService.getUserEntity(request.userId());
        Feedback feedback = feedbackService.getFeedbackEntity(request.feedbackId());

        React react = React.builder()
                .emojiType(request.emojiType())
                .user(user)
                .feedback(feedback)
                .build();

        React saved = reactRepository.save(react);
        return reactMapper.toResponse(reactMapper.toDTO(saved));
    }

    @Override
    public List<ReactResponse> getReactsByFeedbackId(Long feedbackId) {
        return reactRepository.findByFeedbackId(feedbackId).stream()
                .map(reactMapper::toDTO)
                .map(reactMapper::toResponse)
                .toList();
    }

    @Override
    public ReactDTO saveReactFromSocket(ReactDTO dto) {
        var entity = reactMapper.toEntity(dto);
        var saved = reactRepository.save(entity);
        return reactMapper.toDTO(saved);
    }
}