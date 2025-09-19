package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Created by R0manL.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Item {
    private final String caption;
    private final String captionSingle;
    private final Object dropOrder;
    private final String id;
    private final String identificationEnglish;
    private final String identificationGerman;
    private final int lid;
    private final int mainOrder;
    private final int order;
    private final Object planningDataLevel;
    private final Object reportId;
    private final String tableName;
    private final Object entityName;
    private final String globalFilterEntity;
    private final boolean isSingleViewOnly;
    private final Object innerView;
    private final boolean isInAppReport;
    private final UserConsent userConsent;
    private final Object reportButtonsState;
}
