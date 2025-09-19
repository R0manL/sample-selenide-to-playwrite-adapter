package com.superit.smart.qa.ui.smartbox.pages.components.button;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.conditions.Condition.cssClassContains;


/**
 * Created by R0manL.
 */

@Slf4j
public class ButtonWithIconWrapper {
    private static final String IS_ACTIVE_CLASS_NAME = "is-active";
    private static final String IS_BLUE_CLASS_NAME = "is-blue";
    private final WebElm btn;

    public ButtonWithIconWrapper(IconClassName icon, WebElm parent) {
        this.btn = parent.$("*:is(button, [mat-button]):has(" + icon.getClassName() + ")");
    }

    public WebElm getWebElm() {
        return btn;
    }

    public void click() {
        getWebElm().click();
    }

    public void waitToBecomeActive() {
        btn.shouldHas(cssClassContains(IS_ACTIVE_CLASS_NAME));
    }

    public boolean isActive() {
        return btn.containsCssClass(IS_ACTIVE_CLASS_NAME);
    }

    public void waitToBecomeBlue() {
        btn.shouldHas(cssClassContains(IS_BLUE_CLASS_NAME));
    }


    public enum IconClassName {
        ADD(".icon-plus"),
        ADMIN_VIEW(".icon-tab-view-table"),
        CALCULATE(".icon-calculate"),
        IS_CALCULATING(".btn-is-calculating"),
        CALENDAR_VIEW(".icon-date"),
        CANCEL(".icon-closed"),
        CHART_VIEW(".icon-chart"),
        CLOSE(".icon-closed"),
        CONFIRM(".icon-checked"),
        COPY(".icon-wizard-copy"),
        CUSTOMIZE(".icon-customize"),
        DELETE(".icon-delete"),
        DETAIL_VIEW(".icon-zoom-in"),
        DOWNLOAD(".icon-download-button"),
        EDIT(".icon-edit"),
        FILTER(".icon-filter"),
        HISTORY(".icon-planning-history"),
        ICON_RESET(".icon-reset"),
        LOCKED(".icon-lock"),
        MAP_VIEW(".icon-tab-view-map"),
        PARAMETERS_IN_COLUMNS_VIEW(".icon-tab-table-view-column"),
        PARAMETERS_IN_ROWS_VIEW(".icon-tab-table-view-row"),
        RECYCLE(".icon-recycle"),
        REFRESH(".icon-refresh"),
        REMOVE(".btn-remove"),
        RESTORE(".icon-restore"),
        SETTINGS(".icon-settings"),
        SINGLE_ENTITY_VIEW(".icon-tab-view-single"),
        SORTING(".icon-sorting-menu"),
        STRUCTURE_OWNERSHIP(".icon-tab-view-structure"),
        TABLE_VIEW(".icon-tab-view-table"),
        EXPAND_VIEW(".icon-arrow-round-corners"),
        TILES_VIEW(".icon-tab-tiles-view"),
        TUTORIALS(".icon-tutorials"),
        ZOOM_OUT(".icon-zoom-out");

        private final String className;

        IconClassName(@NotNull String className) {
            this.className = className;
        }

        @NotNull
        public String getClassName() {
            return this.className;
        }
    }
}
