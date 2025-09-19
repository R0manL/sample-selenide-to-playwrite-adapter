package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class KeyFactsUI {
    @NotNull
    private final String assetClassification;
    @NotNull
    private final String assetType;
    private final String allocation;
    @NotNull
    private final String assetCurrency;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    @NotNull
    private final String country;
    private final String region;
    @NotNull
    private final String acqEconomicTransitionDate;
    private final String saleEconomicTransitionDate;
}
