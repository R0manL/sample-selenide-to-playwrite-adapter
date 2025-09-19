package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public class PlanningSessionUI {
    private final @NotNull String title;
    private final String version;
    private final String source;
    private final String strategy;
    private final String scenario;
    private final String period;
}
