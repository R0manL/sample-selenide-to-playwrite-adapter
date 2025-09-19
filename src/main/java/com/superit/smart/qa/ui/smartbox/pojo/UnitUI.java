package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UnitUI {

    private final String building;
    private final String unitID;
    private final String shortDesignation;
    private final LocalDate unitStart;
    private final String typeOfUse;
}
