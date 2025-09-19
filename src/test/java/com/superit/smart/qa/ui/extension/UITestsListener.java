package com.superit.smart.qa.ui.extension;

import com.superit.smart.qa.core.enums.User;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.util.Arrays;

import static com.superit.smart.qa.api.smartbox.services.UserSettingsService.setClientIdForUser;
import static com.superit.smart.qa.api.smartbox.services.UserSettingsService.setLanguageOnUI;
import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static java.util.concurrent.TimeUnit.SECONDS;


@Slf4j
public class UITestsListener implements TestExecutionListener {
    private static final String UI_TEST_GROUP = "ui";
    private static final String API_TEST_GROUP = "api";
    private static final String BBOX_TEST_GROUP = "smartbox";
    private static final String TEST_GROUPS = System.getProperty("groups", "none");

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        log.debug("Execution properties: " + ENV_CONFIG.toString());

        //Verify if test groups defined properly
        if(!(TEST_GROUPS.startsWith(UI_TEST_GROUP) || TEST_GROUPS.startsWith(API_TEST_GROUP))) {
            log.error("maven '-Dgroups' must stat with: '{}' or '{}'.", UI_TEST_GROUP, API_TEST_GROUP);
        }

        if(!(TEST_GROUPS.contains(BBOX_TEST_GROUP))) {
            log.error("maven '-Dgroups' must contains AUT group name: '{}' or '{}'.", BBOX_TEST_GROUP);
        }

        log.info(" ------------- API setup: awaitility, api timeouts --------------- ");
        Awaitility.setDefaultTimeout(ENV_CONFIG.awaitilityTimeout(), SECONDS);
        Awaitility.setDefaultPollInterval(ENV_CONFIG.awaitilityPollInterval(), SECONDS);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", ENV_CONFIG.httpSocketTimeout())
                .setParam("http.connection.timeout", ENV_CONFIG.httpConnectionTimeout()));
        log.info(" ------------- API setup: completed --------------- ");

        if(TEST_GROUPS.startsWith(UI_TEST_GROUP)) {
            log.info(" -------------- UI setup: Playwright, UI timeouts ---------------- ");

            // smartbox
            if(TEST_GROUPS.contains(BBOX_TEST_GROUP)) {
                log.info(" ---------- smartbox's UI tests setup: default settings for all users. --- ");
                Arrays.stream(User.values())
                        .forEach(user -> {
                            setClientIdForUser(ENV_CONFIG.clientId(), user);
                            setLanguageOnUI(ENV_CONFIG.lang(), user);
                        });

                log.info(" ---------- Cleanup ---------- ");
                // NONE
                log.info(" ---------- Cleanup complete ---------- ");
                log.info(" ----------- smartbox's UI tests setup: completed --- ");
                log.info(" --------------- UI setup: complete ---------------- ");
            }
        }
    }
}
