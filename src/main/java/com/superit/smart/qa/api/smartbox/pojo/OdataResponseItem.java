package com.superit.smart.qa.api.smartbox.pojo;
import lombok.Data;

import java.util.List;

@Data
public class OdataResponseItem {
    private final Integer key;
    private final String entity;
    private final Object error;
    private final List<OdataResponseSplitProperty> splitProperties;
    private final int changedRecordsCount;
}