package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class AviationAssetUI {
    @NotNull String portfolio;
    @NotNull String company;
    @NotNull String assetId;
    @NotNull String shortDesignation;
    @NotNull String assetType;
    @NotNull String assetClassification;
    @NotNull LocalDate acqEconomicTransitionDate;
}