package com.superit.smart.qa.ui.smartbox.pages.panels;
/* Created by R0manL. */

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.core.playwright.conditions.Condition;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.SideBarModalWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchAndMultiSelectWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithIconWrapper.IconClassName.ADD;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.RESET_TO_DEFAULT;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.SAVE_SETTINGS;

@Slf4j
public class KpiFieldSettingsPanel {
    private static final SideBarModalWrapper MODAL = new SideBarModalWrapper($("control-it-customization-kpi-settings-modal .app-aside"));

    public DropdownWrapper getDropdownWrapper(@NotNull DropdownCaption caption) {
        return MODAL.getDropdownWrapper(caption.getText());
    }

    public DropdownWithSearchWrapper getDropdownWithSearchWrapper(@NotNull DropdownCaption caption) {
        return MODAL.getDropdownWithSearchWrapper(caption.getText());
    }

    public DropdownWithSearchAndMultiSelectWrapper getDropdownWithSearchAndMultiSelectWrapper(@NotNull DropdownCaption caption) {
        return MODAL.getDropdownWithSearchAndMultiSelectWrapper(caption.getText());
    }

    public KpiFieldSettingsPanel select(RadioBtn label) {
        MODAL.selectRadioButton(label.getText());
        return this;
    }

    public KpiFieldSettingsPanel setFromOrOffset(int number) {
        MODAL.setInput(number, InputCaption.FROM_OR_OFFSET.getText());
        return this;
    }

    public KpiFieldSettingsPanel setTO(int number) {
        MODAL.setInput(number, InputCaption.TO.getText());
        return this;
    }

    public KpiFieldSettingsPanel setFromValue1(int number) {
        MODAL.setInput(number, InputCaption.FROM_VALUE1.getText());
        return this;
    }

    public KpiFieldSettingsPanel setToValue1(int number) {
        MODAL.setInput(number, InputCaption.TO_VALUE1.getText());
        return this;
    }

    public KpiFieldSettingsPanel setValue(int number) {
        MODAL.setInput(number, InputCaption.VALUE.getText());
        return this;
    }

    public int getFromValue2() {
        return Objects.requireNonNull(MODAL.getInputValueAsInteger(InputCaption.FROM_VALUE2.getText()), "Value 2 can't be null.");
    }

    public WebElm getFromValue2Elm() {
        return MODAL.getInput(InputCaption.FROM_VALUE2.getText());
    }

    public KpiFieldSettingsPanel setToValue2(int number) {
        MODAL.setInput(number, InputCaption.TO_VALUE2.getText());
        return this;
    }

    public WebElm getToValue2Elm() {
        return MODAL.getInput(InputCaption.TO_VALUE2.getText());
    }

    public KpiFieldSettingsPanel click(ButtonWithTextWrapper.ButtonText btnText) {
        MODAL.click(btnText);

        return this;
    }

    public KpiFieldSettingsPanel click(ButtonWithIconWrapper.IconClassName btnIcon) {
        MODAL.click(btnIcon);

        return this;
    }

    public void clickSaveSettings() {
        MODAL.click(SAVE_SETTINGS);
        MODAL.getRoot().should(Condition.disappear);
    }

    public WebElm getAddClusterBtn() {
        return new ButtonWithIconWrapper(ADD, MODAL.getRoot().$("control-it-cluster-field")).getWebElm();
    }

    public KpiFieldSettingsPanel clickAddCluster() {
        getAddClusterBtn().click();
        return this;
    }

    public KpiFieldSettingsPanel clickResetToDefault() {
        MODAL.click(RESET_TO_DEFAULT);

        return this;
    }

    public WebElm getDeleteClusterSectionButtons() {
        return MODAL.getButton(ButtonWithIconWrapper.IconClassName.DELETE, ".cluster-container");
    }

    public enum DropdownCaption {
        ENTITY_LEVEL("Entity Level:"),
        FILTER_OR_KPI_CRITERIA("Filter/KPI criteria:"),
        VALUES("Values:"),
        OPERATOR("Operator"),
        DATA_LEVEL("Date level:");

        private final String caption;

        DropdownCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum EntityLevelDropdown {
        RENTAL_UNITS("RENTAL UNITS"),
        REAL_ESTATE_ASSETS("REAL ESTATE ASSETS"),
        LEASES_CONTRACTS("LEASE CONTRACTS");

        private final String caption;

        EntityLevelDropdown(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum DataLevelCaption {
        MONTH("Month"),
        QUARTER("Quarter"),
        YEAR("Year");

        private final String caption;

        DataLevelCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum FilterOrKPICriteriaCaption {
        TYPE_OF_USE("Type of use"),
        PHYSICAL_ASSETS_20040("20040 Number of real estate assets"),
        NON_PHYSICAL_ASSETS_003("20030 # non-physical assets"),
        AGE_ASSET("Age (asset)"),
        AGE_OF_REAL_ESTATE_ASSET("Age of the real estate asset"),
        ASSET_TYPE("Asset type"),
        VAT_LIABLE("VAT liable"),
        SUBPORTFOLIO1("Subportfolio 1");

        private final String caption;

        FilterOrKPICriteriaCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum ValuesCaption {
        SELECT_ALL("Select All"),
        BOX_110_RESIDENTIAL("BOX_110 Residential"),
        BOX_120_OFFICE("BOX_120 Office"),
        BOX_130_RETAIL("BOX_130 Retail"),
        PT030_INDUSTRIAL_ESTATE("PT030 Industrial - Estate"),
        TRUE("True");

        private final String caption;

        ValuesCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum InformationAreaSection {
        REAL_ESTATE_ASSETS("Real Estate Assets"),
        FUNDS_PORTFOLIOS_MANDATES("Funds | Portfolios | Mandates");

        private final String caption;

        InformationAreaSection(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum OperatorCaption {
        GREATER_THAN("greater than"),
        CLUSTER("cluster");

        private final String caption;

        OperatorCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum RadioBtn {
        SEPARATE_COLUMN("Separate column"),
        AGGREGATED_COLUMN("Aggregated column");

        private final String caption;

        RadioBtn(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }

    public enum InputCaption {
        FROM_OR_OFFSET("From/Offset:"),
        TO("to:"),
        FROM_VALUE1("From/Value 1:"),
        TO_VALUE1("To/Value 1:"),
        FROM_VALUE2("From/Value 2:"),
        TO_VALUE2("To/Value 2:"),
        VALUE("Value");


        private final String caption;

        InputCaption(@NotNull String caption) {
            this.caption = caption;
        }

        public String getText() {
            return this.caption;
        }
    }
}
