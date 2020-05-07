package com.example.pactconsumerapi.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserCreateRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
