package com.ariffugur.socialmedia.dto;

import com.ariffugur.socialmedia.model.Role;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {
}
