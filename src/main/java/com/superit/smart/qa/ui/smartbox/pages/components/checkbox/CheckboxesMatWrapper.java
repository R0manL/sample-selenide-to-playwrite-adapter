package com.superit.smart.qa.ui.smartbox.pages.components.checkbox;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Represent 'mat-checkbox' UI component.
 */

@Slf4j
public class CheckboxesMatWrapper {
    private final WebElm checkboxes;
    private final CheckboxMatWrapper checkboxMatWrapper;

    public CheckboxesMatWrapper(WebElm parent) {
        this.checkboxMatWrapper = new CheckboxMatWrapper(parent);
        this.checkboxes = this.checkboxMatWrapper.getCheckbox();
    }

    public List<Boolean> areChecked() {
        return this.checkboxes
                .all()
                .stream()
                .map(this.checkboxMatWrapper::isChecked)
                .collect(Collectors.toList());
    }
}
