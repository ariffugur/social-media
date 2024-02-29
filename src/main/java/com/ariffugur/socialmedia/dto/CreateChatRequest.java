package com.ariffugur.socialmedia.dto;

import com.ariffugur.socialmedia.model.User;

public record CreateChatRequest(
        String reqUser,
        String user2
) {
}
