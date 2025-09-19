package com.superit.smart.qa.api.smartbox.pojo.customization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class Groups {
    @JsonProperty("portfolios")
    private final List<Portfolio> portfolios;
    @JsonProperty("companies")
    private final List<Company> companies;
    @JsonProperty("real estate assets")
    private final List<RealEstateAsset> realEstateAssets;
    @JsonProperty("current valuation (valuations)")
    private final List<CurrentValuationValuations> currentValuationValuations;
}
