package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class FinanceAndAccountingUI {
    @NotNull private final String assetCurrency;
    private final String vatOptionRate;
}
