package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Session {
    private Integer lid;
    private String id;
    private String title;
    private String caption;
    private String versionDescription;
    private String description;
    private Integer source;
    private Integer version;
    private Integer strategy;
    private Integer scenario;
    private Integer planProzess;
    private Integer period;
    private String statusDesc;
    private Integer numberOfPeriods;
    private Boolean isFinished;
    private Object statusKey;
}
