package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@SuperBuilder
@Data
public abstract class LeaseWithDebitorUI {
    @NotNull String unit;
    @NotNull String unitId;
    @NotNull String id;
    @NotNull LocalDate start;
    @NotNull LocalDate end;
    boolean temporaryContract;
    boolean vacancyContract;
    int value;
}