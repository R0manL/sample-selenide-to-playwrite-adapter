package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class TopFilterEntityRequest {
    private Integer lid;
    @NotNull
    private String name;
    @NotNull
    private String nameGerman;
    @NotNull
    private String nameEnglish;
    private Boolean isOwned;
    private Boolean isFavorite;
    private Boolean isHidden;
    private Integer propertiesCount;
    private Object kpiFilter;
    private List<Integer> sharedUsers;
    private List<Object> sharedGroups;
    private Boolean isSharedUsers;
    private Boolean isSharedGroups;
    private List<Session> sessions;
    private SourceDataLevel sourceDataLevel;
    private String sourceDataLevelId;
    private String sourceDataLevelName;
    private List<TopFilter> topFilter;
}
