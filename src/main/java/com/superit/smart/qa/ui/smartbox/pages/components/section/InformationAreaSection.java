package com.superit.smart.qa.ui.smartbox.pages.components.section;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.panels.KpiFieldSettingsPanel;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$$;

@Slf4j
public class InformationAreaSection {
    private static final String SECTION_CAPTION = "Information area";

    private static final String SECTION_ROOT_ELM_TEMPLATE = "//control-it-customization-selected-list-column" +
            "[.//*[@class='selected-list-title' and text()='%s']]";
    private static final String GROUP_SECTION_ELM_TEMPLATE = "//*[@class='selected-list-group-title' and text()='%s']/../..";
    private static final String ITEM_TITLE_SECTION_ELM_TEMPLATE = "//h2[normalize-space(text())='%s']/../..";
    private static final String ITEM_SUB_TITLE_SECTION_ELM_TEMPLATE = "//..//*[normalize-space(text())='%s']/..";


    public KpiFieldSettingsPanel openSettingsFor(@NotNull String kpiOrMdName, @NotNull String levelName) {
        final int singleElementIndex = 0;
        return openSettingsFor(kpiOrMdName, levelName, singleElementIndex);
    }

    public KpiFieldSettingsPanel openSettingsFor(@NotNull String kpiOrMdName, @NotNull String levelName, int index) {
        log.info("Open settings for kpi or md: {}, level: {}, index: {}", kpiOrMdName, levelName, index);

        getItemElmsBy(kpiOrMdName, levelName)
                .get(index)
                .$(".icon-settings")
                .click();

        return new KpiFieldSettingsPanel();
    }

    public KpiFieldSettingsPanel openSettingsFor(@NotNull String kpiOrMdName,
                                                 @NotNull String description,
                                                 @NotNull String levelName) {
        log.info("Open settings for kpi or md: {}, description: {}, level: {}",
                kpiOrMdName, description, levelName);

        getItemElmsBy(kpiOrMdName, description, levelName)
                .$(".icon-settings").click();

        return new KpiFieldSettingsPanel();
    }

    public WebElm getDescriptionFor(@NotNull String kpiOrMdName, @NotNull String levelName) {
        return getItemElmsBy(kpiOrMdName, levelName)
                .first()
                .$("//..//*[@tippy and @tabindex='0']");
    }

    private WebElm getItemElmsBy(@NotNull String kpiOrMdName, @NotNull String levelName) {
        return $$(String.format(SECTION_ROOT_ELM_TEMPLATE + GROUP_SECTION_ELM_TEMPLATE + ITEM_TITLE_SECTION_ELM_TEMPLATE, SECTION_CAPTION,
                levelName,
                kpiOrMdName));
    }

    private WebElm getItemElmsBy(@NotNull String kpiOrMdName, @NotNull String description, @NotNull String levelName) {
        return $(String.format(SECTION_ROOT_ELM_TEMPLATE
                        + GROUP_SECTION_ELM_TEMPLATE
                        + ITEM_TITLE_SECTION_ELM_TEMPLATE
                        + ITEM_SUB_TITLE_SECTION_ELM_TEMPLATE,
                SECTION_CAPTION,
                levelName,
                kpiOrMdName,
                description));
    }
}
