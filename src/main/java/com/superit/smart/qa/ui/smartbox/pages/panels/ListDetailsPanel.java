package com.superit.smart.qa.ui.smartbox.pages.panels;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchAndMultiSelectWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$$;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE;

@Slf4j
public class ListDetailsPanel {
    private static final WebElm ROOT_ELM = $("control-it-side-bar-list-settings");

    private ListDetailsPanel() {
        // NONE
    }

    public static ListDetailsPanel getListDetailsMenu() {
        return new ListDetailsPanel();
    }

    public ListDetailsPanel expandAndSelectBy(ListDetailsPanelDropdown dropdown, @NotNull String... texts) {
        expand(dropdown)
                .selectBy(texts)
                .collapse();

        return this;
    }

    public void clickSave() {
        new ButtonWithTextWrapper(SAVE, ROOT_ELM).click();

//        waitOnProgressBarDisappear(ROOT_ELM);
    }

    public DropdownWithSearchAndMultiSelectWrapper expand(ListDetailsPanelDropdown caption) {
        WebElm select = getDropdownSelectElm(caption);
        return new DropdownWithSearchAndMultiSelectWrapper(select)
                .expand();
    }

    public WebElm getSessionNamesElmsFromDropdown() {
        WebElm select = getDropdownSelectElm(ListDetailsPanelDropdown.SESSION);
        WebElm visibleOptions = new DropdownWithSearchAndMultiSelectWrapper(select).getVisibleOptions();
        return getSessionNamesElmsFromDropdownVisibleOptions(visibleOptions);
    }

    public WebElm getFinishedSessionNamesElmsFromDropdown() {
        WebElm select = getDropdownSelectElm(ListDetailsPanelDropdown.SESSION);
        WebElm visibleOptions = new DropdownWithSearchAndMultiSelectWrapper(select)
                .getVisibleOptions()
                .filterByChild(ButtonWithIconWrapper.IconClassName.LOCKED.getClassName());

        return getSessionNamesElmsFromDropdownVisibleOptions(visibleOptions);
    }

    private WebElm getSessionNamesElmsFromDropdownVisibleOptions(WebElm dropdownVisibleOptions) {
        return dropdownVisibleOptions
                .$(".value-name")
                .filterByVisibilityIs(true)
                .shouldBe(Condition.visibleAtLeastOne);
    }

    public WebElm getSharedWithItems() {
        return $$(".shared-item");
    }

    public WebElm getSharedWithItemBy(@NotNull String userName) {
        return getSharedWithItems().filterByText(userName).first();
    }

    public ListDetailsPanel removeSharedWithItemWith(@NotNull String userName) {
        String iconSelector = ButtonWithIconWrapper.IconClassName.CLOSE.getClassName();
        getSharedWithItemBy(userName)
                .$(iconSelector)
                .click();

        return this;
    }

    private WebElm getDropdownSelectElm(ListDetailsPanelDropdown caption) {
        return ROOT_ELM.$("//label[starts-with(normalize-space(), '" + caption.getCaption() + "')]/..//mat-select");
    }


    public enum ListDetailsPanelDropdown {
        SESSION("Session"),
        USER_NAME("User name");

        private final String caption;

        ListDetailsPanelDropdown(@NotNull String caption) {
            this.caption = caption;
        }

        public String getCaption() {
            return this.caption;
        }
    }
}
