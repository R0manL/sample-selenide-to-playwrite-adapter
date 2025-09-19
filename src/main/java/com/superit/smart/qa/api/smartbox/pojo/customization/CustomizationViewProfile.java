package com.superit.smart.qa.api.smartbox.pojo.customization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CustomizationViewProfile {
    private int lid;
    @JsonProperty("name_EN")
    @NotNull
    private String nameEN;
    @JsonProperty("name_DE")
    @NotNull
    private String nameDE;
    private final String shortDescription;
    private final Boolean isApplied;
    private final Boolean isSelected;
    private final Boolean isApplicable;
    private final String resource;
    private final Integer ownerId;
    private final Boolean isEditable;
    private final String customResource;
    private final Integer customizationSettingsType;
    private Groups groups;
    private final Boolean isHaveExcelTemplate;
    private final String excelFileName;
    private final String owner;
    private final Integer order;
    private final Object bannerName;
    private final Boolean isSharedAcrossMenus;
    private final Boolean isArchiveCustomization;
    private final Boolean isMovable;
    private final Boolean isHidden;
    private final String menuName;
    private final Object settingsJson;
    private final Object handledGroups;
    private final Object module;
    private final Integer clientId;
    private final Integer kpiCount;
    private final String iconClass;
}
