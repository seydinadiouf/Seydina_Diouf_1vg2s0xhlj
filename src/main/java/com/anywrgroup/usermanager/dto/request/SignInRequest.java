package com.anywrgroup.usermanager.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest  {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignInRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
