package com.example.SeeLife.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        // represents a String value of USER in the enum Role.
        return name();
    }
}
