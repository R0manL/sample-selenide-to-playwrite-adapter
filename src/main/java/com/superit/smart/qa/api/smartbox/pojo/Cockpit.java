package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * Created by R0manL.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Cockpit {
    private final int id;
    private final String name;
    private final boolean isFavorite;
    private final boolean isEditable;
    private final boolean isShareable;
    private final boolean isOrderFixed;
    private final String nameEN;
    private final String nameDE;
    private final String owner;
    private final String iconName;
    private final Integer parentId;
    @Singular
    private final List<CockpitItem> items;
    private final int order;
}
