package com.superit.smart.qa.ui.smartbox.enums;

import lombok.Getter;

@Getter
public enum CreditRatingAgency {
    NONE("None"),
    FI_FITCH("FI Fitch"),
    SP_STANDARD_AND_POORS("SP Standard & Poors"),
    MO_MOODYS("MO Moodys"),
    DU_DUN_AND_BRADSTREET("DU Dun & Bradstreet");

    private final String agency;

    CreditRatingAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return getAgency();
    }

}