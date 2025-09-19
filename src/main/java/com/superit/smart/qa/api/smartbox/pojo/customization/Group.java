package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {
    private final String caption;
    private final String connectionProperty;
    private final String entityName;
    private final String entityTypeName;
    private final boolean isVisible;
    private final String itemName;
    private final List<KpiGroup> kpiGroup;
    private final String kpiGroupsCaption;
    private final int lid;
    private final String linkType;
    private final String menuSaasId;
    private final String table;
    private final String type;
    private final List<WidgetGroup> widgetGroup;
    private final String widgetGroupsCaption;
    private final int order;
    private final Object relatedDataLevel;
    private final String dataLevelMenuName;
    private final boolean isLeaf;
    private final boolean isInitialLeaf;
    private final boolean isExternalItem;
    private final String parentMenuSaasId;
    private final String specialPathDetailed;
}
