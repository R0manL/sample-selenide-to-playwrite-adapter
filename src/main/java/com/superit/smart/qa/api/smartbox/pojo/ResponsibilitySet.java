package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Builder
public class ResponsibilitySet {
    @JsonProperty("@odata.context")
    private String odataContext;
    private List<Responsibility> value;


    @Getter
    public static class Responsibility {
        private int lid;
    }
}
