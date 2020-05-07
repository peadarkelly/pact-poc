package com.example.pactproviderapi.controllers;

import com.example.pactproviderapi.dtos.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ResponseEntity<StatusResponse> index() {
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponse("PACT Provider API is running"));
    }

}