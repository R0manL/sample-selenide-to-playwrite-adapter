package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KpiGroup {
    private final int dataLevelOrder;
    private final String groupId;
    private final List<SelectedData> kpis;
    private final int lid;
    private final String name;
    private final int order;
    private final int parentLid;
    private final String parentCaption;
    private final int selectedCustomizationDataType;
    private final int typeId;
    private final boolean isForDashboard;
    private final boolean isForCalculation;
    private final boolean isForDashboardOverview;
    private final List<Groups> groups;
}
