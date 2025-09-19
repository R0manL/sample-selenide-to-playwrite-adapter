package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public class ContractUI {
    @NotNull String contractType;
    @NotNull String reference;
    @NotNull String multiplierOnReference;
    @NotNull String paymentInterval;

}
