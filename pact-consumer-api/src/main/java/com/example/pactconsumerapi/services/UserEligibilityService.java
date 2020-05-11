package com.example.pactconsumerapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

class EligibilityResponse implements Serializable {

    private boolean isEligible;

    public EligibilityResponse() {}

    public boolean getIsEligible() {
        return this.isEligible;
    }

    public void setIsEligible(boolean eligible) {
        this.isEligible = eligible;
    }
}

@Service
public class UserEligibilityService {

    public boolean isUserEligible(String email) {
        RestTemplate restTemplate = new RestTemplate();

        EligibilityResponse response = restTemplate.getForObject(
                String.format("http://localhost:8081/is-eligible?email=%s", email),
                EligibilityResponse.class
        );

        return response.getIsEligible();
    }

}
