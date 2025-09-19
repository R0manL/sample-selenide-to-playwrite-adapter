package com.superit.smart.qa.api.smartbox.pojo.customization;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Builder(toBuilder=true)
public class DataUserBlock {
    private final String blockName;
    private final String caption;
    private final Boolean isDefault;
    private final Boolean isRequired;
    private final Integer order;
    private final Boolean enabled;
    private final String saasId;
    private final String blockSize;
    private final Boolean isFixed;
}
