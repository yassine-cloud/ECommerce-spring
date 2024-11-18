package com.iset.ECommerce.dto;

import com.iset.ECommerce.entity.User;

public class AuthResponse {
    private String token;
    private User user;

    // Constructor
    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
