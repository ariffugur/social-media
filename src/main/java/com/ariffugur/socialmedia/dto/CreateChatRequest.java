package com.ariffugur.socialmedia.dto;

import com.ariffugur.socialmedia.model.User;

public record CreateChatRequest(
        User reqUser,
        User user2
) {
}
