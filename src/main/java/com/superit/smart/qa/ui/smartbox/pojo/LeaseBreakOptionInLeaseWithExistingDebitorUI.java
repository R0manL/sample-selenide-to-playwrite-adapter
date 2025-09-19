package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.OptionHolder;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfCancellation;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class LeaseBreakOptionInLeaseWithExistingDebitorUI {
    @NotNull LocalDate date;
    @NotNull OptionHolder optionHolder;
    @NotNull String numberOfPeriods;
    @NotNull TypeOfCancellation typeOfCancellation;
}