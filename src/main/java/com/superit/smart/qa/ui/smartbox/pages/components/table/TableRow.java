package com.superit.smart.qa.ui.smartbox.pages.components.table;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.superit.smart.qa.core.CustomCollectors.toSingleton;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.cssClassContains;
import static com.superit.smart.qa.core.playwright.conditions.Condition.notCssClassContains;

/**
 * Created by R0manL.
 */

@Slf4j
public class TableRow {
    private static final String ROW_LEVEL_CLASS_NAME_PREFIX = "ag-row-level-";
    private static final String ROW_LEVEL_CLASS_NAME_DELIMITER = "-";
    private static final String EXPANDED_CLASS_NAME = "is-btn-expand";

    @Getter private final WebElm row;
    private final WebElm expandCollapseBtn;
    private final String caption;


    public TableRow(@NotNull String withCaption) {
        log.info("Get table row with '{}' caption.", withCaption);
        this.caption = withCaption;
        this.row = $$("//*[@role='row' and .//*[contains(@class,'entity-caption')]" +
                "/*[normalize-space(text())='" + withCaption + "']]")
                .filterByVisibilityIs(true);

        this.expandCollapseBtn = row.$("button.btn-toggle");
    }

    public WebElm getSubRowsElms() {
        log.info("Get sub rows elements.");
        int subRowLevel = getRowLevel() + 1;
        String subRowClassName = ROW_LEVEL_CLASS_NAME_PREFIX + subRowLevel;

        return $$("//*[@role='row' and contains(@class,'" + subRowClassName + "') " +
                "and .//*[contains(@class,'entity-caption')]]")
                .filterByVisibilityIs(true);
    }

    public TableRow expand() {
        log.info("Expand the row.");
        expandCollapseBtn.click();

        return this;
    }

    public TableRow collapse() {
        log.info("Collapse the row.");
        if (isExpanded()) {
            expandCollapseBtn.click();
        } else {
            log.warn("Row has already collapsed. Skip this step.");
        }

        return this;
    }

    public void shouldBeExpanded() {
        expandCollapseBtn.shouldHas(cssClassContains(EXPANDED_CLASS_NAME));
    }

    public void shouldBeCollapsed() {
        expandCollapseBtn.has(notCssClassContains(EXPANDED_CLASS_NAME));
    }

    public int getRowLevel() {
        log.info("Get row's level.");
        String classValue = this.row.getCssClass();

        String levelValue = Arrays.stream(classValue.split(" "))
                .filter(value -> value.contains(ROW_LEVEL_CLASS_NAME_PREFIX))
                .collect(toSingleton());

        String subStr = levelValue.substring(levelValue.lastIndexOf(ROW_LEVEL_CLASS_NAME_DELIMITER) + 1);

        return Integer.parseInt(subStr);
    }

    public boolean isExpanded() {
        return this.row.getCssClass().contains("ag-row-group-expanded");
    }

    @NotNull
    public String getCaption() {
        return this.caption;
    }

    public TableRow select() {
        this.row.click();

        return this;
    }
}
