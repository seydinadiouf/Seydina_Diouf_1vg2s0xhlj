package com.anywrgroup.usermanager.dto.response;

import com.anywrgroup.usermanager.dto.Role;

public class SignInResponse {
    private String username;

    private String token;

    private Role role;

    public SignInResponse(String username, String token, Role role) {
        this.username = username;
        this.token = token;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
