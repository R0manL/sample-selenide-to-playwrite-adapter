package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class DepositsUI {
    String type;
    int debits;
    int credits;
    String currency;
    Boolean subjectToIndexation;
    @NotNull
    LocalDate dueDate;
    LocalDate expiryDate;
    String comment;
}