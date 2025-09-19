package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetInformationUI {
    private final String yearOfConstruction;
    private final String yearOfReconstruction;
    private final String plotSizeInSqm;
    private final String assetMeasure;
    private final String usefulLife;
}