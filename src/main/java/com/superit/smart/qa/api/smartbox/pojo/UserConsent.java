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
public class UserConsent {
    private final boolean isDeletable;
    private final boolean isEditable;
    private final boolean isInsertable;
    private final boolean isVisible;
    private final String saasId;
}
