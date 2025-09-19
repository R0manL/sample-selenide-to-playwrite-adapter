package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@Builder
public class AttributeUI {
    @NotNull String group;
    @NotNull LocalDate validFrom;
    String comment;
    String attribute;
    String attributeId;
    String keyValue;
}