package com.example.pactproviderapi.dtos;

public class StatusResponse {

    private String message;

    public StatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
