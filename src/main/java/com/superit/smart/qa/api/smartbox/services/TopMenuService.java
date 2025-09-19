package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.Menu;
import com.superit.smart.qa.api.smartbox.pojo.Module;
import com.superit.smart.qa.core.smartbox.enums.MenuModule;
import com.superit.smart.qa.core.enums.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.core.CustomCollectors.toSingleton;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by R0manL on 09.06.2021.
 */

@Slf4j
public class TopMenuService {
    private static final String ENDPOINT_PATH = "/webuiservice/api/webui/view/topmenu";

    private TopMenuService() {
        // None
    }

    @NotNull
    public static Module getTopMenuModule(MenuModule module, User user) {
        log.info("API: Getting '{}' menu module (page).", module);

        return getTopMenu(user)
                .getModuleMenu()
                .stream().filter(m -> module.getCaption().equals(m.getCaption()))
                .collect(toSingleton());
    }

    public static int getTopMenuModuleId(MenuModule module, User user) {
        log.info("API: Getting '{}' menu module's lid.", module.getCaption());

        return getTopMenuModule(module, user).getLid();
    }

    @NotNull
    private static Menu getTopMenu(User user) {
        log.debug("API: Getting menu for: {}.", user.getUserName());

        return setupBBRequestSpecFor(user)
                .basePath(ENDPOINT_PATH)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(Menu.class);
    }
}
