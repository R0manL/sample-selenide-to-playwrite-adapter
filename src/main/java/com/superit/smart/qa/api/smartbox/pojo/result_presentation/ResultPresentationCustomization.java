package com.superit.smart.qa.api.smartbox.pojo.result_presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ResultPresentationCustomization {
        @JsonProperty("lid")
        private final Integer lid;
        @JsonProperty("name")
        private final String name;
        @JsonProperty("name_EN")
        private final String nameEN;
        @JsonProperty("name_DE")
        private final String nameDE;
        @JsonProperty("shortDescription")
        private final String shortDescription;
        @JsonProperty("isApplied")
        private final Boolean isApplied;
        @JsonProperty("isApplicable")
        private final Boolean isApplicable;
        @JsonProperty("resource")
        private final String resource;
        @JsonProperty("ownerId")
        private final Integer ownerId;
        @JsonProperty("isEditable")
        private final Boolean isEditable;
        @JsonProperty("customResource")
        private final String customResource;
        @JsonProperty("customizationSettingsType")
        private final Integer customizationSettingsType;
        @JsonProperty("settingsJson")
        private final String settingsJson;
        @JsonProperty("module")
        private final String module;
        @JsonProperty("clientId")
        private final Integer clientId;
        @JsonProperty("owner")
        private final String owner;
        @JsonProperty("order")
        private final Integer order;
        @JsonProperty("bannerName")
        private final Object bannerName;
        @JsonProperty("isSharedAcrossMenus")
        private final Boolean isSharedAcrossMenus;
        @JsonProperty("isMovable")
        private final Boolean isMovable;
        @JsonProperty("isHidden")
        private final Boolean isHidden;
        @JsonProperty("menuName")
        private final Object menuName;
        @JsonProperty("settings")
        private final SettingsCustomization settings;
        @JsonProperty("isHaveExcelTemplate")
        private final Boolean isHaveExcelTemplate;
        @JsonProperty("excelFileName")
        private final String excelFileName;
        @JsonProperty("excelFileNameList")
        private final List<Object> excelFileNameList;
        @JsonProperty("excelFileExtraKey")
        private final String excelFileExtraKey;
}
