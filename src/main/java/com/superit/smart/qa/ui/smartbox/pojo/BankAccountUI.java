package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public class BankAccountUI {
    private @NotNull String bankId;
    private @NotNull String accountId;
    private @NotNull String accountDescription;
    private @NotNull String accountHolder;
    private @NotNull String accountNumber;
    private @NotNull String businessPartner;
}