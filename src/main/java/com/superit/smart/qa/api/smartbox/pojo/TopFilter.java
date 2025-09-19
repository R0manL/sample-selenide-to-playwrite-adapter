package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class TopFilter {
    @NotNull
    private String operationType;
    @NotNull
    private String binary;
    @NotNull
    private String entityName;
    private String kpiTextIdentity;
    private boolean containsGlobalVariable;
    @NotNull
    private List<Object> values;
    @NotNull
    private Integer group;
    @NotNull
    private Integer order;
    private String propertyName;
    private Map<String, Object> additionalProperties;
    private Object current;
    private Object foreignColumn;
    private Object keyMappingId;
    private Object relatedEntities;
    private Object keyMapping;
    private boolean showHiddenEntities;
}
