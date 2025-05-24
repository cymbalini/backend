package com.app.backend.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Integer role; // Add more fields as needed

    public AuthResponse(String token, Integer role) {
        this.token = token;
        this.role = role;
    }
}
