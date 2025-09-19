package com.superit.smart.qa.api.smartbox.pojo.customization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
public class CustomizationView {
    private String customResource;
    @NotNull
    @JsonProperty("name_DE")
    private String nameDE;
    @NotNull
    @JsonProperty("name_EN")
    private String nameEN;
    private String resource;
    private String shortDescription;
    @Nullable
    private Integer customizationId;
}
