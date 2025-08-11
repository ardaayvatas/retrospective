package com.ardaayvatas.retrospective.data.response;

import com.ardaayvatas.retrospective.data.dto.UserDTO;

public record ReactResponse(
        Long id,
        String emojiType,
        UserDTO user
) {}