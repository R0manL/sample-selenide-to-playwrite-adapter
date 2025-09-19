package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupTreeVersionRequest {
    private final String customResource;
    private final String dataLevel;
    private final String dataType;
    private final Boolean isDashboard;
    private final String resource;
    private final String viewType;
}
