package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministrationUI {
    private final Boolean investmentInAsset;
    private final String assetClassification;
    private final String allocation;
    private final String bulkClusterType;
    private final String leadingBasisOfMeasure;
    private final String internetLink;
}
