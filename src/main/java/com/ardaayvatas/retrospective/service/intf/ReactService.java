package com.ardaayvatas.retrospective.service.intf;

import com.ardaayvatas.retrospective.data.dto.ReactDTO;
import com.ardaayvatas.retrospective.data.request.CreateReactRequest;
import com.ardaayvatas.retrospective.data.response.ReactResponse;
import java.util.List;

public interface ReactService {
    ReactResponse createReact(CreateReactRequest request);
    List<ReactResponse> getReactsByFeedbackId(Long feedbackId);
    ReactDTO saveReactFromSocket(ReactDTO dto);
}
