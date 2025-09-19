package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class AcquisitionUI {
    @NotNull
    private final LocalDate acqEconomicTransitionDate;
    private final LocalDate paymentDate;
    private final LocalDate notaryDateAcquisition;
    private final LocalDate contractDate;
    private final String purchasePrice;
    private final String acquisitionCurrency;
    private final String acquisitionType;
    private final String currencyRateAtAcquisition;
    private final String commentAcquisition;
}