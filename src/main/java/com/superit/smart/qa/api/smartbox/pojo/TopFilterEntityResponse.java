package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class TopFilterEntityResponse {
    private int lid;
    @NotNull
    private String name;
    @NotNull
    private String nameGerman;
    @NotNull
    private String nameEnglish;
    @JsonProperty(value = "isOwned")
    private boolean isOwned;
    private String owner;
    @JsonProperty(value = "isFavorite")
    private boolean isFavorite;
    private int order;
    @JsonProperty(value = "isSharedByMe")
    private boolean isSharedByMe;
    @JsonProperty(value = "isSharedWithMe")
    private boolean isSharedWithMe;
    private int propertiesCount;
    @JsonProperty(value = "lazyLoadCounter")
    private boolean lazyLoadCounter;
    @JsonProperty(value = "isHidden")
    private boolean isHidden;
    @JsonProperty(value = "isEditable")
    private boolean isEditable;
    private List<TopFilter> topFilter;
    private SourceDataLevel sourceDataLevel;
    private List<Session> sessions;
    private List<Integer> sharedUsers;
    private List<Object> sharedGroups;
    private List<AdvSession> advSessions;
}