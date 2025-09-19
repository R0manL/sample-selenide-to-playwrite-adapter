package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Builder
public class BuildingUI {
    @NotNull
    private final String building;
    @NotNull
    private final String buildingId;
    @NotNull
    private final String designation;
    @NotNull
    private final LocalDate startDate;
    @NotNull
    private final String street;
    @NotNull
    private final String houseNumber;
    @NotNull
    private final String postalCode;
}
