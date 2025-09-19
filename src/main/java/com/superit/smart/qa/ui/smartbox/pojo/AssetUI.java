package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public final class AssetUI {
    @NotNull
    private final String portfolio;
    @NotNull
    private final String portfolioId;
    @NotNull
    private final String companyName;
    @NotNull
    private final String companyId;
    @NotNull
    private final String assetId;
    @NotNull
    private final String shortDesignation;
    @NotNull
    private final String acqEconomicTransitionDate;
    @NotNull
    private final String assetType;
    @NotNull
    private final String scoringCluster;
    @NotNull
    private final String country;
    @NotNull
    private final String street;
    @NotNull
    private final String houseNumber;
    @NotNull
    private final String postalCode;
    @NotNull
    private final String city;
    @NotNull
    private final String federalState;
    @NotNull
    private final Integer latitude;
    @NotNull
    private final Integer longitude;
    private final String assetClassification;
    private final String allocation;
    private final String assetCurrency;
}
