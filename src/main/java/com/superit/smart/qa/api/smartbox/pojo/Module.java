package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/* Created by R0manL. */

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Module {
    private final String caption;
    private final List<Object> folders;
    private final Integer id;
    private final String identificationEnglish;
    private final String identificationGerman;
    private final Boolean isBottomBlock;
    private final Boolean isStructure;
    private final Boolean cockpitView;
    private final List<Item> items;
    private final Integer lid;
    private final Integer order;
    private final String tableName;
    private final Integer parentId;
    private final Integer minUserModeAccessLayer;
    private final MenuUserConsent userConsent;
}
