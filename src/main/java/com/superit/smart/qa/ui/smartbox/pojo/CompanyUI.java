package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyUI {
    private final String companyId;
    //Company part at wizard
    @NotNull
    private final String shortDesignation;
    @NotNull
    private final String designation;
    private final String currencyCompany;
    private final byte startFiscalYear;
    private final String companyCategory;
    @NotNull
    private final String assetShortDesignation;
    @NotNull
    private final LocalDate acqEconomicTransitionDate;
    private final String portfolio;
    private final String portfolioId;
}
