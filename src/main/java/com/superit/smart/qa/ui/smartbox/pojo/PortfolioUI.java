package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public class PortfolioUI {
    //Portfolios part at wizard
    @NotNull
    private final String portfolioId;
    @NotNull
    private final String portfolioShortDesignation;
    @NotNull
    private final String portfolioDesignation;
    private final String portfolioFundsCategory;
    private final String portfolioFundType;
    private final String portfolioCurrency;
    private final String portfolioStartFiscalYear;
    private final String portfolioLegalForm;
    //Company part at wizard
    private final String companyId;
    @NotNull
    private final String companyShortDesignation;
    @NotNull
    private final String companyDesignation;
    private final String companyCurrency;
    private final String companyStartFiscalYear;
    private final String companyLegalForm;
    private final String companyType;
    private final String companyCategory;
    private final String companyClassification;
    //Asset part at wizard
    @NotNull
    private final String assetId;
    @NotNull
    private final String assetShortDesignation;
    @NotNull
    private final String assetAcqEconomicTransitionDate;
    private final String assetCurrency;
    private final String assetType;
    private final String assetCountry;
    private final String assetClassification;
    private final String assetAllocation;
    //Location part at wizard
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
}
