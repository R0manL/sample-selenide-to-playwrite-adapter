package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WidgetGroup {
    private final List<SelectedData> customColumnGroupItems;
    private final int dataLevelOrder;
    private final String groupId;
    private final String parentCaption;
    private final String widgetCaption;
    private final int widgetId;
    private final Integer replacementGroupId;
}
