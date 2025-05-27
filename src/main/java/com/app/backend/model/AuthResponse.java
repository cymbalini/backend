package com.app.backend.model;

import lombok.Data;

@Data
public class AuthResponse {
    private Integer id;
    private String token;
    private Integer role; // Add more fields as needed

    public AuthResponse(String token, Integer role, Integer id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }
}
