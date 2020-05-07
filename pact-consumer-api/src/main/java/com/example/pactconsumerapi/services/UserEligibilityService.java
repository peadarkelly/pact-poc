package com.example.pactconsumerapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

class EligibilityResponse {

    private boolean isEligible;

    public EligibilityResponse() {}

    public boolean isEligible() {
        return this.isEligible;
    }

    public void setEligible(boolean eligible) {
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

        return response.isEligible();
    }

}
