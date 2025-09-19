package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

import java.util.List;

@Data
public class LinkValue {
    private Boolean isAccessible;
    private Integer key;
    private String accessReason;
    public String itemStatus;
    private List<InternalLinkValue> values;
    private String tenantId;
}