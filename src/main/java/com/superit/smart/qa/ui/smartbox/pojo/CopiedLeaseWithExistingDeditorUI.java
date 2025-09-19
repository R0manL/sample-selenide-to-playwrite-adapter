package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CopiedLeaseWithExistingDeditorUI {
    private final String unit;
    private final String id;
    private final LocalDate start;
    private final String debitor;
    final int numberOfPeriods;
    private final LocalDate end;
    private final String value;
    private final String currency;
    private final String debitPositionType;
}