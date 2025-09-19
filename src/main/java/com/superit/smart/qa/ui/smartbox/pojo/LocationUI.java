package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationUI {
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String country;
    private final String federalState;
    private final String region;
    private final String locationCluster;
    private final String locationQuality;
    private final String latitude;
    private final String longitude;
    private final String alternativeReportingAddress;
}