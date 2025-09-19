package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.result_presentation.ResultPresentation;
import com.superit.smart.qa.api.smartbox.pojo.result_presentation.ResultPresentationCustomization;
import com.superit.smart.qa.api.smartbox.pojo.result_presentation.ResultPresentationCustomizationResponse;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class ResultPresentationService {
    private static final String RESULT_PRESENTATION_ENDPOINT_PATH = "/webuiservice/api/webui/ResultPresentation";
    private static final String RESULT_PRESENTATION_ENDPOINT_GET_PATH = RESULT_PRESENTATION_ENDPOINT_PATH + "/LazyLoad";


    private ResultPresentationService() {
        //NONE
    }

    public static int createResultPresentation(ResultPresentation resultPresentation, User user) {
        log.debug("API: Create customization view.");

        return setupBBRequestSpecFor(user)
                .basePath(RESULT_PRESENTATION_ENDPOINT_PATH)
                .body(resultPresentation)
                .when()
                .put()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(Integer.class);
    }

    public static List<ResultPresentationCustomization> getResultPresentationPreviewCustomizationList(User user) {
        log.debug("API: Get result presentation preview customization list.");

        ResultPresentationCustomizationResponse resultPresentationCustomizations = setupBBRequestSpecFor(user)
                .basePath(RESULT_PRESENTATION_ENDPOINT_GET_PATH)
                .body("{\"resource\":\"132\",\"customResource\":\"tblPreview\",\"filterString\":\"\",\"skip\":0,\"take\":20000}")
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(ResultPresentationCustomizationResponse.class);

        return resultPresentationCustomizations.getResultPresentations();
    }

    public static List<ResultPresentationCustomization> getResultPresentationDetailsCustomizationList(User user) {
        log.debug("API: Get result presentation details customization list.");

        ResultPresentationCustomizationResponse resultPresentationCustomizations = setupBBRequestSpecFor(user)
                .basePath(RESULT_PRESENTATION_ENDPOINT_GET_PATH)
                .body("{\"resource\":\"132\",\"customResource\":\"tblFull\",\"filterString\":\"\",\"skip\":0,\"take\":20000}")
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(ResultPresentationCustomizationResponse.class);

        return resultPresentationCustomizations.getResultPresentations();
    }

    public static void deleteResultPresentationCustomization(User user, int id) {
        log.debug("API: Delete result presentation customization with id " + id);

        setupBBRequestSpecFor(user)
                .basePath(RESULT_PRESENTATION_ENDPOINT_PATH + "/" + id)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }
}
