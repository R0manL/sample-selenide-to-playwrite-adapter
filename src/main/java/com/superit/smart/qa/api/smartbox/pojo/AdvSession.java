package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class AdvSession {
    private final Integer lid;
    private final String id;
    private final String title;
    private final String caption;
    private final String versionDescription;
    private final String description;
    private final Integer source;
    private final Integer version;
    private final Integer strategy;
    private final Integer scenario;
    private final Integer planProzess;
    private final Integer period;
    private final Object statusDesc;
    private final Integer numberOfPeriods;
    private final Boolean isFinished;
    private final Object statusKey;
}
