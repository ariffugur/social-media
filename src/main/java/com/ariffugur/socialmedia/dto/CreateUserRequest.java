package com.ariffugur.socialmedia.dto;

import com.ariffugur.socialmedia.model.Role;

public record CreateUserRequest(
        String username,
        String password,
        String name,
        Role role
) {
}
