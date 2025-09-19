package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SaleUI {
    private final LocalDate saleEconomicTransitionDate;
    private final LocalDate dateOfPaymentSale;
    private final LocalDate notaryDateSale;
    private final String salesPrice;
    private final String salesCurrency;
    private final String grossSalesPrice;
    private final String typeOfSale;
    private final LocalDate purchaseContactDate;
    private final String commentSale;
    private final String currencyRateAtSale;
}
