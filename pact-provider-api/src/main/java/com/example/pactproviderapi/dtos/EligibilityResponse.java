package com.example.pactproviderapi.dtos;

public class EligibilityResponse {

    private boolean isEligible;

    public EligibilityResponse(boolean isEligible) {
        this.isEligible = isEligible;
    }

    public boolean isEligible() {
        return this.isEligible;
    }

    public void setEligible(boolean eligible) {
        this.isEligible = eligible;
    }
}
