package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.CalculationRequest;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by R0manL.
 */

@Slf4j
public class CalculationAPIService {
    private static final String CALCULATION_ENDPOINT = "/webuiservice/api/webui/Calculations/CalcCoreBatchProperty";

    private CalculationAPIService() {
        // None
    }

    public static CalculationAPIService getCalculationAPIService() {
        return new CalculationAPIService();
    }

    public void executeCalculationFor(CalculationRequest request, @NotNull String clientId, User user) {
        log.info("Execute calculation with parameters: {}, for client: {}", request, clientId);

        setupBBRequestSpecFor(user, clientId)
                .basePath(CALCULATION_ENDPOINT)
                .body(request)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK);
    }
}
