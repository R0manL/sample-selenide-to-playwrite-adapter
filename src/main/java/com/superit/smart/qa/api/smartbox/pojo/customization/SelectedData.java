package com.superit.smart.qa.api.smartbox.pojo.customization;
/* Created by R0manL. */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class SelectedData {
    private final String unit;
    private final Object dateTo;
    private final Object dateFrom;
    private final Object deadlineAdjustmentMonths;
    private final int order;
    private final boolean isSelected;
    private final boolean isDefault;
    private final Object valueFormat;
    private final boolean isTimeDependent;
    private final Object key;
    private final String calculation;
    private final String description;
    private final int columnWidth;
    private final String nameShort;
    private final String name;
    private final String fullName;
    private final String textIdentity;
    private final int lid;
    private final Object parentLid;
    private final int positionType;
    private final int typeId;
    private final String parentCaption;
    private final String groupId;
    private final int dataLevelOrder;
    private final int selectedCustomizationDataType;
    private final String connectionProperty;
    private final boolean isRequiredFilter;
    private final Object dateLevel;
    private final Object calendarType;
    private final boolean isDateLevelEditable;
    private final boolean isCalendarTypeEditable;
    private final List<Object> filterBlocks;
    private final boolean isAggregated;
    private final Object numberOfAggregation;
    private final Object dateNumberTo;
    private final Object customizedName;
    private final Object sessionDetailId;
    private final boolean isSessionEditable;
    private final Object sessionDetailLid;
    private final boolean isDeleted;
    private final boolean isNumberOfAggregationEditable;
    private final boolean isDeadlineAdjustmentMonthsEditable;
    private final Object customName;
    private final boolean isEqualValue;
    private final String caption;
    @JsonProperty("caption_EN")
    private final Object captionEN;
    @JsonProperty("caption_DE")
    private final Object captionDE;
    private final String fieldId;
    private final Object filter;
    private final boolean isFixed;
    private final Object menuSaasId;
    private final int widgetId;
    private final String widgetName;
    @JsonProperty("widgetName_EN")
    private final Object widgetNameEN;
    @JsonProperty("widgetName_DE")
    private final Object widgetNameDE;
    private final String connectionField;
    private final boolean useCalcTrigger;
    private final Object replacementGroupId;
    @JsonProperty("parentCaption_EN")
    private final Object parentCaptionEN;
    @JsonProperty("parentCaption_DE")
    private final Object parentCaptionDE;
    private final Object shortCaption;
    private final Object rawKpiId;
    private final Object cluster;
    private final Object customizedNameEn;
    private final Object customizedNameDe;
    private final String id;
    private final Boolean isClustered;
    private final Object currencyLevel;
    private final boolean isCurrencyLevelEditable;
    private final boolean isAccessGranted;
    private final Object accounts;
    private final Object clusterBlocks;
}
