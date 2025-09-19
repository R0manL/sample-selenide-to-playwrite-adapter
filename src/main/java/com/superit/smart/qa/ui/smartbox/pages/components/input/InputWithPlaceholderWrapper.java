package com.superit.smart.qa.ui.smartbox.pages.components.input;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.InputPlaceholder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Created by R0manL.
 */

@Slf4j
public class InputWithPlaceholderWrapper extends InputWrapper {

    public InputWithPlaceholderWrapper(InputPlaceholder inputPlaceholder, WebElm parent) {
        super(parent.$("input[placeholder='" + inputPlaceholder + "']"));
    }

    public InputWithPlaceholderWrapper(InputPlaceholder inputPlaceholder, @NotNull String dataAttrQaValue, WebElm parent) {
        super(parent.$("input[placeholder='" + inputPlaceholder + "'][data-attr-qa='" + dataAttrQaValue + "']"));
    }

    public InputWithPlaceholderWrapper search(@NotNull String text) {
        clear()
                .sendKeys(text)
                .click(); // Note. Sometimes after searching, result option can't be selected without clicking.

        return this;
    }
}
