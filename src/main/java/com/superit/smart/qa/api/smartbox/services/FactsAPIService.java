package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.FactsAPIDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Created by R0manL on 09.06.2021.
 */

@Slf4j
public class FactsAPIService {

    private FactsAPIService() {
        // NONE
    }

    public static FactsAPIDetails fromRequestDetails(@NotNull String requestDetails) {
        log.info("Convert request details value to facts API details object");
        try {
            return new ObjectMapper().readValue(requestDetails, FactsAPIDetails.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Can't convert to object:" + requestDetails + "\nError:" + e.getMessage());
        }
    }
}
