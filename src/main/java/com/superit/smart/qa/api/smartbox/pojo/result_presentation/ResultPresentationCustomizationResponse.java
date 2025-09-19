package com.superit.smart.qa.api.smartbox.pojo.result_presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ResultPresentationCustomizationResponse {
        @JsonProperty("count")
        public Integer count;
        @JsonProperty("resultPresentations")
        public List<ResultPresentationCustomization> resultPresentations;
}
