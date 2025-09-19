package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

import java.util.List;

@Data
public class LinkItem {
    private String entityName;
    private String fieldName;
    private String linkMenuId;
    private int generalCount;
    private Boolean isUserRightsApplied;
    private String itemSaasId;
    private List<LinkValue> values;
    private Object encodedLids;
}