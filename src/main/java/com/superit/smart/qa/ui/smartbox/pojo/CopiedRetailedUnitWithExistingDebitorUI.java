package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CopiedRetailedUnitWithExistingDebitorUI {

    private final String building;
    private final String unitID;
    private final String shortDesignation;
    private final LocalDate unitStart;
    private final String typeOfUse;
    private final String value;
    private final String measureValue;
    private final String measureType;
    private final String unit;
    private final String debitor;
    private final String debitorId;
    // leases section
    private final String id;
}
