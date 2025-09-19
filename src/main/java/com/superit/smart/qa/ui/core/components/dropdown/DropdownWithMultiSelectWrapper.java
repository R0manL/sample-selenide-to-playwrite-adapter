package com.superit.smart.qa.ui.core.components.dropdown;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.checkbox.CheckboxMatWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.list.ListOfElmsWithScroller;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Slf4j
public class DropdownWithMultiSelectWrapper {
    private final DropdownWrapper dropdownWrapper;

    public DropdownWithMultiSelectWrapper(WebElm input) {
        this.dropdownWrapper = new DropdownWrapper(input);
    }

    public DropdownWithMultiSelectWrapper expandAndSelectIfNotSelectedBy(@NotNull String text) {
        expandAndSelectIfNotSelectedBy(new String[]{text});
        return this;
    }

    public DropdownWithMultiSelectWrapper expandAndSelectIfNotSelectedBy(@NotNull String... texts) {
        dropdownWrapper.expand();
        Arrays.stream(texts).toList().forEach(this::select);
        dropdownWrapper.collapse();

        return this;
    }

    public WebElm getTitlesOfVisibleOptions() {
        return dropdownWrapper.getTitlesOfVisibleOptions();
    }

    public WebElm getVisibleOptions() {
        return dropdownWrapper.getVisibleOptions();
    }

    public DropdownWithMultiSelectWrapper expand() {
        dropdownWrapper.expand();
        return this;
    }

    public void collapse() {
        dropdownWrapper.collapse();
    }

    private void select(@NotNull String text) {
        WebElm optionElms = dropdownWrapper.getVisibleOptions();
        WebElm option = new ListOfElmsWithScroller(optionElms).findFirstChildBy(text);
        CheckboxMatWrapper checkbox = new CheckboxMatWrapper(option);

        if (!checkbox.isChecked()) {
            checkbox.select(true);
        } else {
            log.info("Option: '{}' has already selected. Skip. ", text);
        }
    }

    public WebElm getSelectedOption() {
       return dropdownWrapper.getSelectedOption();
    }
}
