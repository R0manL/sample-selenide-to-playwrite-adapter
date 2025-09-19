package com.superit.smart.qa.ui.core.components.dropdown;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.list.ListOfElmsWithScroller;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.CLEAR;


@Slf4j
public class DropdownWithSearchAndMultiSelectWrapper extends DropdownWithSearchWrapper {

    public DropdownWithSearchAndMultiSelectWrapper(WebElm select) {
        super(select);
    }

    @Override
    public DropdownWithSearchAndMultiSelectWrapper expand() {
        super.expand();
        return this;
    }

    @Override

    public DropdownWithSearchAndMultiSelectWrapper search(@NotNull String text) {
        super.search(text);
        return this;
    }

    @Override
    public DropdownWithSearchAndMultiSelectWrapper scrollAndSelectFirstByText(@NotNull String text) {
        new ListOfElmsWithScroller(getVisibleOptions())
                .findFirstChildBy(text)
                .click();

        return this;
    }

    public DropdownWithSearchAndMultiSelectWrapper selectBy(@NotNull String... texts) {
        Arrays.stream(texts).toList().forEach(this::scrollAndSelectFirstByText);

        return this;
    }

    public DropdownWithSearchAndMultiSelectWrapper searchAndSelectOptionsBy(@NotNull String... texts) {
        return searchAndSelectOptionsBy(Arrays.stream(texts).collect(Collectors.toList()));
    }

    public DropdownWithSearchAndMultiSelectWrapper searchAndSelectOptionsBy(List<String> texts) {
        texts.forEach(text -> this.search(text).scrollAndSelectFirstByText(text));
        return this;
    }

    public DropdownWithSearchAndMultiSelectWrapper clickClear() {
        new ButtonWithTextWrapper(CLEAR, DROPDOWN).click();
        return this;
    }
}
