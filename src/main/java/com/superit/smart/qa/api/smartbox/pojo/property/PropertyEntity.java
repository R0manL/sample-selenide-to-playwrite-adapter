package com.superit.smart.qa.api.smartbox.pojo.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class PropertyEntity {
    @Singular
    private List<Item> items;
    @NotNull
    private String name;
    @NotNull
    private String nameToShow;
    @NotNull
    private String menuId;
    @Singular
    private List<PropertyVal> entityItemValues;
}
