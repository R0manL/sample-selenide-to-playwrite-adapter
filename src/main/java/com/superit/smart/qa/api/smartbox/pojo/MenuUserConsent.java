package com.superit.smart.qa.api.smartbox.pojo;

import lombok.Data;

@Data
public class MenuUserConsent {
    private final Boolean isDeletable;
    private final Boolean isEditable;
    private final Boolean isInsertable;
    private final Boolean isVisible;
    private final String saasId;
}
