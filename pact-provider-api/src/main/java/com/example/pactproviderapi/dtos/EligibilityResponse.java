package com.example.pactproviderapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EligibilityResponse {

    private boolean isEligible;

    public EligibilityResponse(boolean isEligible) {
        this.isEligible = isEligible;
    }

    @JsonProperty("isEligible")
    public boolean isEligible() {
        return this.isEligible;
    }

    public void setEligible(boolean eligible) {
        this.isEligible = eligible;
    }
}
