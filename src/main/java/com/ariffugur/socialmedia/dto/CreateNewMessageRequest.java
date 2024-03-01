package com.ariffugur.socialmedia.dto;

public record CreateNewMessageRequest(
        String content,
        String image
) {
}
