package com.superit.smart.qa.api.smartbox.pojo.result_presentation;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CompareOptions {
    private final String version;
    private final String baseVersion;
    private final String comparisonType;
    private final Object versionVariable;
}
