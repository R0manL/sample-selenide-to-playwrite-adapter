package com.superit.smart.qa.api.smartbox.pojo.customization;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentValuationValuations {
    private final int selectedCustomizationDataType;
    private final SelectedData selectedData;
    private final int positionType;
}
