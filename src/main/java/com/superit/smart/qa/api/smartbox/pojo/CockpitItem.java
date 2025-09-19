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
public class CockpitItem {
    private final String description;
    private final String descriptionEnglish;
    private final String descriptionGerman;
    private final String externalId;
    private final Integer id;
    private final Boolean isInAnyGroup;
    private final String lastCallDate;
    private final String name;
    private final String objectsOfConsideratioin;
    private final String pageURL;
    private final Boolean isFavorite;
    private final String iconName;
    private final Integer orderInGroup;
    private final Boolean isItemOrderFixed;
    private final Boolean isEditable;
    private final String saasId;
    private final String reportButtonsState;
    private final Boolean isInAppReport;
    private final Boolean isStandardReport;
    private final String filterMenuSaasId;
}