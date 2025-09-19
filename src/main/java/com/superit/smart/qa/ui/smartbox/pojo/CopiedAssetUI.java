package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CopiedAssetUI {
    private final String portfolioId;
    private final String companyName;
    private final String assetId;
    private final String shortDesignation;
    private final String acqEconomicTransitionDate;
    private final String assetType;
    private final String scoringCluster;
    private final String country;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String federalState;
    private final String latitude;
    private final String longitude;
    private final String assetClassification;
    private final String allocation;
    private final String assetCurrency;
}