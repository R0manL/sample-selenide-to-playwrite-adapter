package com.superit.smart.qa.ui.smartbox.pages.modals;

import com.superit.smart.qa.ui.smartbox.pages.components.modal.TableCreatingModalWrapper;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;


public class MarketValueDetailsModal {
    private static final TableCreatingModalWrapper MODAL = new TableCreatingModalWrapper();

    public void clickSave() {
        MODAL.clickSave();
    }

    public void clickClose() {
        MODAL.clickClose();
    }

    public MarketValueDetailsModal searchAndSelectFrom(Dropdown dropdown, @NotNull String option) {
        MODAL.getDropdownWithSearchWrapper(dropdown.getCaption())
                .expand()
                .searchThenSelect(option);

        return this;
    }

    @Getter
    public enum Dropdown {
        USE_TYPE("Use type: *");

        private final String caption;

        Dropdown(@NotNull String caption) {
            this.caption = caption;
        }
    }
}
