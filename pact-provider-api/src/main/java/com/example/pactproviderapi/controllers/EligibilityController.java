package com.example.pactproviderapi.controllers;

import com.example.pactproviderapi.dtos.EligibilityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class EligibilityController {

    @RequestMapping("/is-eligible")
    public ResponseEntity<EligibilityResponse> isEligible(@RequestParam String email) {
        final boolean isEligible = email.contains("@gmail.com");
        return ResponseEntity.status(HttpStatus.OK).body(new EligibilityResponse(isEligible));
    }
}