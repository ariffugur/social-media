package com.ariffugur.socialmedia.model;

public enum Role {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");
    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
