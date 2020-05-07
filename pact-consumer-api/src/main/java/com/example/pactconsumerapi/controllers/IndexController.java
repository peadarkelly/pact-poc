package com.example.pactconsumerapi.controllers;

import com.example.pactconsumerapi.dtos.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ResponseEntity<StatusResponse> index() {
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponse("PACT Consumer API is running"));
    }

}