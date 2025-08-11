package com.ardaayvatas.retrospective.service.impl;

import com.ardaayvatas.retrospective.data.dto.CommentDTO;
import com.ardaayvatas.retrospective.data.mapper.CommentMapper;
import com.ardaayvatas.retrospective.data.model.entity.Comment;
import com.ardaayvatas.retrospective.data.model.entity.Feedback;
import com.ardaayvatas.retrospective.data.model.entity.User;
import com.ardaayvatas.retrospective.data.model.repository.CommentRepository;
import com.ardaayvatas.retrospective.data.request.CreateCommentRequest;
import com.ardaayvatas.retrospective.data.response.CommentResponse;
import com.ardaayvatas.retrospective.service.intf.CommentService;
import com.ardaayvatas.retrospective.service.intf.FeedbackService;
import com.ardaayvatas.retrospective.service.intf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse createComment(CreateCommentRequest request) {
        User user = userService.getUserEntity(request.userId());
        Feedback feedback = feedbackService.getFeedbackEntity(request.feedbackId());

        Comment comment = Comment.builder()
                .content(request.content())
                .user(user)
                .feedback(feedback)
                .build();

        Comment saved = commentRepository.save(comment);
        return commentMapper.toResponse(commentMapper.toDTO(saved));
    }

    @Override
    public List<CommentResponse> getCommentsByFeedbackId(Long feedbackId) {
        return commentRepository.findByFeedbackIdOrderByCreatedAtAsc(feedbackId).stream()
                .map(commentMapper::toDTO)
                .map(commentMapper::toResponse)
                .toList();
    }

    @Override
    public CommentDTO saveCommentFromSocket(CommentDTO dto) {
        var entity = commentMapper.toEntity(dto);
        var saved = commentRepository.save(entity);
        return commentMapper.toDTO(saved);
    }
}