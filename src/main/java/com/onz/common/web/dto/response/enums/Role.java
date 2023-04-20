package com.onz.common.web.dto.response.enums;

import lombok.Getter;

@Getter
public enum Role {
    SUPER_ADMIN("ROLE_SUPER_ADMIN,ROLE_ADMIN"),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
