package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.dto.CommentDTO;
import com.ardaayvatas.retrospective.data.request.CreateCommentRequest;
import com.ardaayvatas.retrospective.data.response.CommentResponse;
import java.util.List;

public interface CommentService {
    CommentResponse createComment(CreateCommentRequest request);
    List<CommentResponse> getCommentsByFeedbackId(Long feedbackId);
    CommentDTO saveCommentFromSocket(CommentDTO dto);
}
