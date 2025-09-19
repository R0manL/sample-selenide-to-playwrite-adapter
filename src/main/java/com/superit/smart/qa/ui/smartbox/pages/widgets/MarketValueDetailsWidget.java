package com.superit.smart.qa.ui.smartbox.pages.widgets;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.WidgetID;
import com.superit.smart.qa.ui.smartbox.pages.components.widget.TableWidgetWrapper;
import com.superit.smart.qa.ui.smartbox.pages.modals.MarketValueDetailsModal;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MarketValueDetailsWidget {
    private static final TableWidgetWrapper WIDGET_WRAPPER = new TableWidgetWrapper(WidgetID.MARKET_VALUE_DETAILS);

    public MarketValueDetailsModal clickEdit() {
        WIDGET_WRAPPER.clickEdit();

        return new MarketValueDetailsModal();
    }

    public MarketValueDetailsModal clickAdd() {
        WIDGET_WRAPPER.clickAdd();

        return new MarketValueDetailsModal();
    }

    public void clickSave() {
        WIDGET_WRAPPER.clickSave();
    }

    public MarketValueDetailsWidget updateCell(ColId colId, @NotNull String newValue, @NotNull String oldUnique) {
        WIDGET_WRAPPER.updateTableDropdown(colId.getValue(), newValue, List.of(oldUnique));
        return this;
    }

    public WebElm getCell(ColId id) {
        return WIDGET_WRAPPER.getCellsBy(id.getValue());
    }

    @Getter
    public enum ColId {
        USE_TYPE("usetyp");

        private final String value;

        ColId(@NotNull String value) {
            this.value = value;
        }
    }
}