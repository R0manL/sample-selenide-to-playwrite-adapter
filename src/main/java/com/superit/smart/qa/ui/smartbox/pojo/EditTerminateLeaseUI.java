package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EditTerminateLeaseUI {

    private final LocalDate date;
}
