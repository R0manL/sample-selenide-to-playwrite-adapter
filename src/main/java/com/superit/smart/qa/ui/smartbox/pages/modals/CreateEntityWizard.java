package com.superit.smart.qa.ui.smartbox.pages.modals;

import com.superit.smart.qa.core.playwright.WebElm;
import com.superit.smart.qa.ui.smartbox.enums.OptionHolder;
import com.superit.smart.qa.ui.smartbox.enums.TypeOfCancellation;
import com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.checkbox.CheckboxMatWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ListModalWrapper;
import com.superit.smart.qa.ui.smartbox.pages.components.modal.ModalWrapper;
import com.superit.smart.qa.ui.smartbox.pojo.*;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWithSearchWrapper;
import com.superit.smart.qa.ui.core.components.dropdown.DropdownWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$;
import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.$$;
import static com.superit.smart.qa.core.playwright.conditions.Condition.*;
import static com.superit.smart.qa.ui.smartbox.pages.components.button.ButtonWithTextWrapper.ButtonText.*;
import static com.superit.smart.qa.ui.smartbox.pages.modals.CreateEntityWizard.Dropdown.FUND_PORTFOLIO_MANDATE_REQUIRED;
import static com.superit.smart.qa.utils.WaitUtils.*;
import static java.time.LocalDate.now;

@Slf4j
public class CreateEntityWizard {
    private final ModalWrapper modal;
    private static final WebElm WIZARD_ROOT_ELM = $("control-it-wizard-menu");
    private static final WebElm RIGHT_SIDE_ROOT_ELM = WIZARD_ROOT_ELM.$("#wizard");

    public CreateEntityWizard() {
        this.modal = new ModalWrapper(WIZARD_ROOT_ELM);
    }

    public CreateEntityWizard fillRentedUnitWithExistingDebitorValues(RentedUnitWithExistingDebitorUI entity) {
        select(Entity.RENTED_UNIT_WITH_EXISTING_TENANT);

        modal.selectDropdownOption(entity.getBuilding(), Dropdown.LAND_AND_BUILDING_REQUIRED.getCaption());
        setUnitId(entity.getBuilding(), entity.getUnitID());

        modal.setDate(entity.getUnitStart(), Input.UNIT_START_REQUIRED.getCaption());
        modal.selectDropdownOption(entity.getTypeOfUse(), Dropdown.TYPE_OF_USE_REQUIRED.getCaption());
        modal.setInput(entity.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());

        clickNext();

        modal.setDate(entity.getStartDate(), Input.START_REQUIRED.getCaption());
        modal.setInput(entity.getValue(), Input.VALUE_REQUIRED.getCaption());
        modal.setDate(entity.getStartDate(), Input.START_REQUIRED.getCaption());

        clickNext();

        modal.setDate(entity.getStartDate(), Input.START_REQUIRED.getCaption());
        modal.selectDropdownOption(entity.getMeasureType(), Dropdown.MEASURE_TYPE_REQUIRED.getCaption());
        modal.setInput(entity.getMeasureValue(), Input.MEASURE_VALUE_REQUIRED.getCaption());
        modal.selectDropdownOption(entity.getUnit(), Dropdown.UNIT_REQUIRED.getCaption());
        modal.setDate(entity.getUnitStart(), Input.START_REQUIRED.getCaption());

        clickNext();

        selectDropdownOptionID(entity.getDebitorId(), Dropdown.DEBITORS_REQUIRED);
        setRadioButton(entity.isTemporaryContract(), RadioButton.TEMPORARY_CONTRACT);

        return this;
    }

    public CreateEntityWizard fillLeaseWithExistingDebitorOnFirstStep(LeaseWithExistingTenantUI leaseWithExistingTenantUI) {

        selectDropdownOptionID(leaseWithExistingTenantUI.getUnitId(), Dropdown.RENTAL_UNIT_REQUIRED);
        selectDropdownOptionID(leaseWithExistingTenantUI.getDebitorId(), Dropdown.DEBITORS_REQUIRED);
        modal.setInput(leaseWithExistingTenantUI.getId(), Input.ID_REQUIRED.getCaption());
        modal.setDate(leaseWithExistingTenantUI.getStart(), Input.START_REQUIRED.getCaption());

        setRadioButton(leaseWithExistingTenantUI.isTemporaryContract(), RadioButton.TEMPORARY_CONTRACT);
        return this;
    }

    public void createRentedUnitWithExistingTenant(LeaseWithExistingTenantUI leaseWithExistingTenantUI) {
        select(Entity.LEASE_WITH_EXISTING_TENANT);
        fillLeaseWithExistingDebitorOnFirstStep(leaseWithExistingTenantUI);

        clickNext();

        modal.setDate(leaseWithExistingTenantUI.getEnd(), Input.END_REQUIRED.getCaption());
        modal.setInput(leaseWithExistingTenantUI.getValue(), Input.VALUE_REQUIRED.getCaption());

        clickNext();
        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);

        waitOnLoadersDisappearAndCheckErrors();
    }

    public void createValuationEntity(ValuationUI valuationUI) {
        select(Entity.VALUATION);

        modal.selectDropdownOption(valuationUI.getAssetId(), Dropdown.ASSET_REQUIRED.getCaption());
        modal.setDate(valuationUI.getDateOfAppraisal(), Input.DATE_OF_APPRAISAL_REQUIRED.getCaption());
        modal.selectDropdownOption(valuationUI.getRealEstateAppraisalType(), Dropdown.REAL_ESTATE_APPRAISAL_TYPE_REQUIRED.getCaption());
        modal.setInput(valuationUI.getMarketValue(), Input.MARKET_VALUE_REQUIRED.getCaption());

        modal.clickSave();
    }

    public void createMaintenance(MaintenanceUI maintenanceUI) {
        select(Entity.MAINTENANCE);

        modal.selectDropdownOption(maintenanceUI.getAsset(), Dropdown.ASSET.getCaption());
        modal.selectDropdownOption(maintenanceUI.getContractType(), Dropdown.CONTRACT_TYPE_REQUIRED.getCaption());
        modal.selectDropdownOption(maintenanceUI.getContractPartner(), Dropdown.CONTRACT_PARTNER_REQUIRED.getCaption());

        modal.setInput(maintenanceUI.getContractId(), Input.CONTRACT_ID_REQUIRED.getCaption());
        modal.setInput(maintenanceUI.getDesignation(), Input.DESIGNATION_REQUIRED.getCaption());

        clickNext();

        modal.setInput(maintenanceUI.getConditionId(), Input.CONDITION_ID_REQUIRED.getCaption());
        modal.setDate(maintenanceUI.getStart(), Input.START_REQUIRED.getCaption());
        modal.setDate(maintenanceUI.getStart(), Input.END.getCaption());
        modal.setDate(maintenanceUI.getNoticePeriodInMonth(), Input.NOTICE_PERIOD_IN_MONTHS_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void createLeaseBreakOption(LeaseBreakOptionUI leaseBreakOptionUI) {
        select(Entity.LEASE_BREAK_OPTION);

        selectDropdownOptionID(leaseBreakOptionUI.getLease(), Dropdown.LEASE_CONTRACT_REQUIRED);

        modal.setDate(leaseBreakOptionUI.getDate(), Input.DATE_REQUIRED.getCaption());
        modal.selectDropdownOption(leaseBreakOptionUI.getOptionHolder().toString(), Dropdown.OPTION_HOLDER_REQUIRED.getCaption());
        modal.setInput(leaseBreakOptionUI.getNumberOfPeriods(), Input.NUMBER_OF_PERIODS_REQUIRED.getCaption());
        modal.selectDropdownOption(leaseBreakOptionUI.getTypeOfCancellation().toString(), Dropdown.TYPE_OF_CANCELLATION_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public CreateEntityWizard createLeaseIndex(LeaseIndexInLeaseWithExistingDebitorUI leaseIndexUI) {
        clickAddLeaseIndex();
        fillLeaseIndexWith(leaseIndexUI);

        return this;
    }

    public CreateEntityWizard fillLeaseIndexWith(LeaseIndexInLeaseWithExistingDebitorUI leaseIndexUI) {
        modal.selectDropdownOption(leaseIndexUI.getIndexSeries(), Dropdown.INDEX_SERIES_REQUIRED.getCaption());
        modal.selectDropdownOption(leaseIndexUI.getTypeOfCheck(), Dropdown.TYPE_OF_CHECK.getCaption());
        modal.selectDropdownOption(leaseIndexUI.getIndexFreeType(), Dropdown.INDEX_FREE_TYPE.getCaption());
        modal.setInput(leaseIndexUI.getIndexValueWithAdjustment(), Input.INDEX_VALUE_LAST_ADJUSTMENT.getCaption());
        modal.setInput(leaseIndexUI.getIndexFreeMonth(), Input.INDEX_FREE_MONTHS.getCaption());
        modal.setInput(leaseIndexUI.getComment(), Input.COMMENT.getCaption());

        return this;
    }

    public void setLeaseBreakOptions(LeaseBreakOptionInLeaseWithExistingDebitorUI leaseBreakOptionUI) {
        WebElm section = getLeaseBreakOptionsSections().last().scrollIntoView();

        modal.setDate(leaseBreakOptionUI.getDate(), Input.DATE_REQUIRED.getCaption(), section);
        modal.selectDropdownOption(leaseBreakOptionUI.getOptionHolder().toString(), Dropdown.OPTION_HOLDER_REQUIRED.getCaption(), section);
        modal.setInput(leaseBreakOptionUI.getNumberOfPeriods(), Input.NUMBER_OF_PERIODS_REQUIRED.getCaption(), section);
        modal.selectDropdownOption(leaseBreakOptionUI.getTypeOfCancellation().toString(), Dropdown.TYPE_OF_CANCELLATION_REQUIRED.getCaption(), section);
    }

    public CreateEntityWizard createCheck(CheckInLeaseWithExistingDebitorUI leaseIndexUI) {
        clickAddCheck();
        fillCheckWith(leaseIndexUI);

        return this;
    }

    public CreateEntityWizard fillCheckWith(CheckInLeaseWithExistingDebitorUI leaseIndexUI) {
        modal.setDate(leaseIndexUI.getDateOfTheFirstIndexReference(), Input.DATE_OF_THE_FIRST_INDEX_REFERENCE.getCaption());
        modal.setDate(leaseIndexUI.getDateOfLastIndexing(), Input.DATE_OF_LAST_INDEXING.getCaption());
        modal.setDate(leaseIndexUI.getEndOfIndexAgreement(), Input.END_OF_INDEX_AGREEMENT.getCaption());
        modal.setInput(leaseIndexUI.getReviewFrequencyInMonth(), Input.REVIEW_FREQUENCY_IN_MONTHS.getCaption());
        modal.setInput(leaseIndexUI.getFixedMonthOfAdjustment(), Input.FIXED_MONTH_OF_ADJUSTMENT.getCaption());
        modal.setInput(leaseIndexUI.getAdaptionOffsetInMonths(), Input.ADAPTION_OFFSET_IN_MONTHS.getCaption());
        modal.setInput(leaseIndexUI.getNumberOfRemainingAdjustments(), Input.NUMBER_OF_REMAINING_AGREEMENTS.getCaption());
        modal.setInput(leaseIndexUI.getTransferOfIndexIncrease(), Input.TRANSFER_OF_INDEX_INCREASE.getCaption());

        return this;
    }

    public void createThreshold(ThresholdInLeaseWithExistingDebitorUI leaseIndexUI) {
        clickAddThreshold();
        fillThresholdWith(leaseIndexUI);
    }

    public void fillThresholdWith(ThresholdInLeaseWithExistingDebitorUI leaseIndexUI) {
        modal.setInput(leaseIndexUI.getThreshold(), Input.THRESHOLD.getCaption());
        modal.setInput(leaseIndexUI.getMinimumAdjustmentFrequencyAfterIndexing(), Input.MINIMUM_ADJUSTMENT_FREQUENCY_AFTER_INDEXING.getCaption());
        modal.setInput(leaseIndexUI.getMinimumIncrease(), Input.MINIMUM_INCREASE.getCaption());
        modal.setInput(leaseIndexUI.getMinimumIncreaseCurrency(), Input.MINIMUM_INCREASE_CURRENCY.getCaption());
        modal.setInput(leaseIndexUI.getMaximumIncrease(), Input.MAXIMUM_INCREASE.getCaption());
        modal.setInput(leaseIndexUI.getMaximumIncreaseCurrency(), Input.MAXIMUM_INCREASE_CURRENCY.getCaption());
    }

    public void createUnitEntity(UnitUI unitUI) {
        fillUnitEntityWith(unitUI);

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void fillUnitEntityWith(UnitUI unitUI) {
        select(Entity.UNIT);

        modal.selectDropdownOption(unitUI.getBuilding(), Dropdown.LAND_AND_BUILDING_REQUIRED.getCaption());
        modal.setDate(unitUI.getUnitStart(), Input.UNIT_START_REQUIRED.getCaption());
        modal.selectDropdownOption(unitUI.getTypeOfUse(), Dropdown.TYPE_OF_USE_REQUIRED.getCaption());
        modal.setInput(unitUI.getUnitID(), Input.UNIT_ID_REQUIRED.getCaption());
        modal.setInput(unitUI.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());
    }

    public void createCompany(CompanyUI companyUI) {
        select(Entity.COMPANY);
        modal.setInputIfNotNull(companyUI.getCompanyId(), Input.COMPANY_ID_REQUIRED.getCaption());
        modal.setInput(companyUI.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());
        modal.setInput(companyUI.getDesignation(), Input.DESIGNATION_REQUIRED.getCaption());
        modal.setInput(companyUI.getStartFiscalYear(), Input.START_FISCAL_YEAR_REQUIRED.getCaption());

        clickNext();

        selectDropdownOptionID(companyUI.getPortfolioId(), FUND_PORTFOLIO_MANDATE_REQUIRED);
        modal.setInput(companyUI.getAssetShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());
        modal.setDate(now(), Input.ACQ_ECONOMIC_TRANSITION_DATE_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void createDebitEntity(DebitUI debitUI) {
        select(Entity.DEBIT);

        modal.setDate(debitUI.getStart(), Input.START_REQUIRED.getCaption());
        modal.setDate(debitUI.getEnd(), Input.END_REQUIRED.getCaption());
        modal.selectDropdownOption(debitUI.getDebitPositionType(), Dropdown.DEBIT_POSITION_TYPE_REQUIRED.getCaption());
        modal.setInput(debitUI.getValue(), Input.VALUE_REQUIRED.getCaption());
        modal.selectDropdownOption(debitUI.getCurrency(), Dropdown.CURRENCY_REQUIRED.getCaption());
        setMonthCheckboxesValues(debitUI);

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);

        WIZARD_ROOT_ELM.should(disappear);
    }

    public LeaseBreakOptionInLeaseWithExistingDebitorUI getLeaseBreakOptionsBy(int orderIndex) {
        WebElm section = getLeaseBreakOptionsSections().get(orderIndex);

        return LeaseBreakOptionInLeaseWithExistingDebitorUI.builder()
                .date(modal.getInputDate(Input.DATE_REQUIRED.getCaption(), section))
                .optionHolder(OptionHolder.from(modal.getDropdownSelectedOption(Dropdown.OPTION_HOLDER_REQUIRED.getCaption(), section)))
                .numberOfPeriods(modal.getInputValue(Input.NUMBER_OF_PERIODS_REQUIRED.getCaption(), section))
                .typeOfCancellation(TypeOfCancellation.from(modal.getDropdownSelectedOption(Dropdown.TYPE_OF_CANCELLATION_REQUIRED.getCaption(), section)))
                .build();
    }

    public WebElm getLeaseBreakOptionsSections() {
        return WIZARD_ROOT_ELM.$$("//form[contains(@class, 'wizard-item-form') " +
                "and .//*[contains(text(),'Lease Break Options')]]/form");
    }

    public void createBuildingEntity(BuildingUI building) {
        select(Entity.BUILDING);

        modal.selectDropdownOption(building.getBuilding(), Dropdown.ASSET_REQUIRED.getCaption());
        modal.setInput(building.getBuildingId(), Input.BUILDING_ID_REQUIRED.getCaption());
        modal.setInput(building.getDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());

        modal.setInput(building.getStreet(), Input.STREET_REQUIRED.getCaption());
        modal.setInput(building.getHouseNumber(), Input.HOUSE_NUMBER_REQUIRED.getCaption());
        modal.setInput(building.getPostalCode(), Input.POSTAL_CODE_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void copyLeaseEntity(CopiedLeaseWithExistingDeditorUI copiedLeaseWithExistingDeditorUI) {
        select(Entity.LEASE_WITH_EXISTING_TENANT);

        modal.selectDropdownOptionIfNotNull(copiedLeaseWithExistingDeditorUI.getUnit(), Dropdown.UNIT_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(copiedLeaseWithExistingDeditorUI.getDebitor(), Dropdown.DEBITORS_REQUIRED.getCaption());
        modal.setInputIfNotNull(copiedLeaseWithExistingDeditorUI.getId(), Input.ID_REQUIRED.getCaption());
        modal.setDateIfNotNull(copiedLeaseWithExistingDeditorUI.getStart(), Input.START_REQUIRED.getCaption());

        clickNext();

        modal.setDateIfNotNull(copiedLeaseWithExistingDeditorUI.getStart(), Input.START_REQUIRED.getCaption());
        modal.setDateIfNotNull(copiedLeaseWithExistingDeditorUI.getEnd(), Input.END_REQUIRED.getCaption());

        modal.getInput(Input.VALUE_REQUIRED.getCaption()).clear();
        modal.setInputIfNotNull(copiedLeaseWithExistingDeditorUI.getValue(), Input.VALUE_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(copiedLeaseWithExistingDeditorUI.getCurrency(), Dropdown.CURRENCY_REQUIRED.getCaption());
        modal.selectDropdownOption(copiedLeaseWithExistingDeditorUI.getDebitPositionType(), Dropdown.DEBIT_POSITION_TYPE_REQUIRED.getCaption());

        clickNext();
        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void copyUnitEntity(CopiedRetailedUnitWithExistingDebitorUI unitUI) {
        select(Entity.RENTED_UNIT_WITH_EXISTING_TENANT);

        modal.selectDropdownOptionIfNotNull(unitUI.getBuilding(), Dropdown.LAND_AND_BUILDING_REQUIRED.getCaption());
        modal.setDateIfNotNull(unitUI.getUnitStart(), Input.UNIT_START_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(unitUI.getTypeOfUse(), Dropdown.TYPE_OF_USE_REQUIRED.getCaption());
        modal.setInputIfNotNull(unitUI.getUnitID(), Input.UNIT_ID_REQUIRED.getCaption());
        modal.setInputIfNotNull(unitUI.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());

        clickNext();

        modal.setInputIfNotNull(unitUI.getValue(), Input.VALUE_REQUIRED.getCaption());

        clickNext();

        modal.selectDropdownOptionIfNotNull(unitUI.getMeasureType(), Dropdown.MEASURE_TYPE_REQUIRED.getCaption());
        modal.setInputIfNotNull(unitUI.getMeasureValue(), Input.MEASURE_VALUE_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(unitUI.getUnit(), Dropdown.UNIT_REQUIRED.getCaption());

        clickNext();

        selectDropdownOptionID(unitUI.getDebitorId(), Dropdown.DEBITORS_REQUIRED);
        modal.setInputIfNotNull(unitUI.getId(), Input.ID_REQUIRED.getCaption());

        clickNext();
        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void copyUnitEntityWithoutChildElements(CopiedUnitUI unitUI) {
        select(Entity.UNIT);

        modal.selectDropdownOptionIfNotNull(unitUI.getBuilding(), Dropdown.LAND_AND_BUILDING_REQUIRED.getCaption());
        modal.setDateIfNotNull(unitUI.getUnitStart(), Input.UNIT_START_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(unitUI.getTypeOfUse(), Dropdown.TYPE_OF_USE_REQUIRED.getCaption());
        modal.setInputIfNotNull(unitUI.getUnitID(), Input.UNIT_ID_REQUIRED.getCaption());
        modal.setInputIfNotNull(unitUI.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
    }

    public void copyAssetEntity(CopiedAssetUI assetUI) {
        select(Entity.ASSET);

        modal.selectDropdownOptionIfNotNull(assetUI.getPortfolioId(), Dropdown.PORTFOLIO_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getCompanyName(), Dropdown.COMPANY_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getAssetId(), Input.ASSET_ID_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getShortDesignation(), Input.SHORT_DESIGNATION_REQUIRED.getCaption());
        modal.setDateIfNotNull(assetUI.getAcqEconomicTransitionDate(), Input.ACQ_ECONOMIC_TRANSITION_DATE_REQUIRED.getCaption());

        modal.selectDropdownOptionIfNotNull(assetUI.getPortfolioId(), FUND_PORTFOLIO_MANDATE_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getAssetCurrency(), Dropdown.ASSET_CURRENCY_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getScoringCluster(), Dropdown.SCORING_CLUSTER_1.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getCountry(), Dropdown.COUNTRY_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getAssetClassification(), Dropdown.ASSET_CLASSIFICATION_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getAssetType(), Dropdown.ASSET_TYPE_REQUIRED.getCaption());

        clickNext();

        modal.setInputIfNotNull(assetUI.getStreet(), Input.STREET_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getHouseNumber(), Input.HOUSE_NUMBER_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getPostalCode(), Input.POSTAL_CODE_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getCity(), Input.CITY_REQUIRED.getCaption());
        modal.selectDropdownOptionIfNotNull(assetUI.getFederalState(), Dropdown.FEDERAL_STATE_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getLatitude(), Input.LATITUDE_REQUIRED.getCaption());
        modal.setInputIfNotNull(assetUI.getLongitude(), Input.LONGITUDE_REQUIRED.getCaption());

        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
        waitOnRoundLoaderMightAppearThenDisappear();
        waitOnLoadersDisappearAndCheckErrors();
    }


    public WebElm getSaveBtn() {
        return modal.getSaveBtn();
    }

    public void clickSave() {
        modal.click(ButtonWithTextWrapper.ButtonText.SAVE);
        waitOnRoundLoaderMightAppearThenDisappear(WIZARD_ROOT_ELM);
    }

    public void clickClose() {
        modal.clickClose(".wizard-closed-wrap");
    }

    public CreateEntityWizard clickNext() {
        new ButtonWithTextWrapper(NEXT, RIGHT_SIDE_ROOT_ELM).click();
        return this;
    }

    public CreateEntityWizard clickBack() {
        new ButtonWithTextWrapper(BACK, WIZARD_ROOT_ELM).click();

        return this;
    }

    public CreateEntityWizard clickAddLeaseBreakOptions() {
        new ButtonWithTextWrapper(ADD_LEASE_BREAK_OPTIONS, WIZARD_ROOT_ELM).click();

        return this;
    }

    public CreateEntityWizard clickAddLeaseIndex() {
        new ButtonWithTextWrapper(ADD_LEASE_INDEX, WIZARD_ROOT_ELM).click();

        return this;
    }

    public CreateEntityWizard clickAddCheck() {
        new ButtonWithTextWrapper(ADD_CHECK, WIZARD_ROOT_ELM).click();

        return this;
    }

    public CreateEntityWizard clickAddThreshold() {
        new ButtonWithTextWrapper(ADD_THRESHOLD, WIZARD_ROOT_ELM).click();

        return this;
    }


    private void setUnitId(@NotNull String valueThatShouldBeUpdated, @NotNull String unitId) {
        String expectedValue = valueThatShouldBeUpdated.substring(0, valueThatShouldBeUpdated.indexOf("Building"));

        modal.getInput(Input.UNIT_ID_REQUIRED.getCaption()).should(valueContains(expectedValue));
        modal.setInput(unitId, Input.UNIT_ID_REQUIRED.getCaption());
    }

    public void selectAndConfirmStreet(@NotNull String value) {
        modal.getInput(Input.STREET_REQUIRED.getCaption()).setValue(value);
        $(".pac-container").shouldBe(visible);
        $$(".pac-container .pac-item").first().click();
    }

    public void setMonthCheckboxesValues(DebitUI debitUI) {
        WebElm monthTableCells = getMonthTableCells();
        for (int i = 0; i < monthTableCells.size(); i++) {
            CheckboxMatWrapper checkboxWrapper = new CheckboxMatWrapper(monthTableCells.get(i));
            boolean state = Boolean.TRUE.equals(debitUI.getMonths().get(i));
            checkboxWrapper.select(state);
        }
    }

    public List<Boolean> getMonthCheckboxesValues() {
        return getMonthTableCells()
                .all()
                .stream()
                .map(cell -> new CheckboxMatWrapper(cell).isChecked())
                .toList();
    }

    public WebElm getInput(Input value) {
        return modal.getInput(value.getCaption());
    }

    public void setInput(@NotNull String value, Input caption) {
        modal.setInput(value, caption.getCaption());
    }

    public void setInput(int value, Input caption) {
        modal.setInput(value, caption.getCaption());
    }

    public void setInput(float value, Input caption) {
        modal.setInput(value, caption.getCaption());
    }

    @NotNull
    public String getInputValue(Input caption) {
        return modal.getInputValue(caption.getCaption());
    }

    public void setDate(@NotNull LocalDate date, Input caption) {
        modal.setDate(date, caption.getCaption());
    }

    public void selectDropdownOption(@NotNull String value, CreateEntityWizard.Dropdown caption) {
        modal.selectDropdownOption(value, caption.getCaption());
    }

    public DropdownWrapper getDropdownWrapper(CreateEntityWizard.Dropdown caption) {
        return modal.getDropdownWrapper(caption.getCaption());
    }

    public DropdownWithSearchWrapper getDropdownWithSearchWrapper(CreateEntityWizard.Dropdown caption) {
        return modal.getDropdownWithSearchWrapper(caption.getCaption());
    }

    @NotNull
    public ListModalWrapper getListModalWrapper(Dropdown dropdown) {
        WebElm selectElm = modal.getInput(dropdown.getCaption());
        return new ListModalWrapper(selectElm);
    }

    private WebElm getMonthTableCells() {
        return RIGHT_SIDE_ROOT_ELM.$$(".month-table-cell");
    }

    public CreateEntityWizard selectDropdownOptionID(@Nullable String id, Dropdown dropdown) {
        if (id != null) {
            WebElm selectElm = modal.getField(dropdown.getCaption()).$("input");
            new ListModalWrapper(selectElm).expandSearchAndSelectByStartWith(id, ListModalWrapper.ColID.ID);
        } else {
            log.warn("ID is null. Skip selection.");
        }

        return this;
    }

    public CreateEntityWizard selectDropdownOptionDesignation(@Nullable String designation, Dropdown dropdown) {
        if (designation != null) {
            WebElm selectElm = modal.getField(dropdown.getCaption()).$("input");
            new ListModalWrapper(selectElm).expandSearchAndSelectByStartWith(designation, ListModalWrapper.ColID.DESIGNATION);
        } else {
            log.warn(ListModalWrapper.ColID.DESIGNATION.getValue() + " is null. Skip selection.");
        }

        return this;
    }

    public void setRadioButton(boolean value, RadioButton radioBitton) {
        modal.setRadioButtonTo(value, radioBitton.getCaption());
    }

    public CreateEntityWizard select(Entity name) {
        WIZARD_ROOT_ELM.$("//*[contains(@class, 'wizard-item') and normalize-space(text())='" + name.getName() + "']").click();
        waitOnRoundLoaderMightAppearThenDisappear(WIZARD_ROOT_ELM);
        return this;
    }

    @Getter
    public enum Entity {
        ASSET("Asset"),
        AVIATION_ASSET("Aviation Asset"),
        BANK_ACCOUNTS("Bank accounts"),
        BUILDING("Building"),
        BUSINESS_PARTNERS("Business Partners"),
        COMPANY("Company"),
        DEBIT("Debit"),
        ENERGY_ASSET("Energy Asset"),
        EQUITY("Equity"),
        FEE("Fee"),
        FINANCIAL_INSTITUTION("Financial institution"),
        INSURANCE_PARTNER("Insurance Partner"),
        INSURANCE_CLAIMS("Insurance claims"),
        INVESTMENT_WITH_CAPITAL_CALL("Investment with capital call"),
        LEASE_BREAK_OPTION("Lease Break Option"),
        LEASE_WITH_EXISTING_TENANT("Lease with existing tenant"),
        LEASE_WITH_NEW_TENANT("Lease with new tenant"),
        LEGAL_CASES("Legal Cases"),
        LEGAL_PARTNER("Legal Partner"),
        LOAN_CONDITION("Loan condition"),
        LOAN_WITH_EXISTING_CREDITOR("Loan with existing creditor"),
        MAINTENANCE("Maintenance"),
        OPEX("Opex"),
        OTHER_COSTS_AND_INCOME("Other Costs and income"),
        PORTFOLIO("Portfolio"),
        PORTFOLIO_INCL_COMPANY_AND_ASSET("Portfolio (incl. company & asset)"),
        RENTED_UNIT_WITH_EXISTING_TENANT("Rented unit with existing tenant"),
        RESIDUAL_DEBT_CHANGE("Residual debt change"),
        RESPONSIBILITY("Responsibility"),
        SHAREHOLDER_LOAN("Shareholder loan"),
        TENANT("Tenant"),
        UNIT("Unit"),
        UNIT_MEASURE("Unit measure"),
        VALUATION("Valuation"),
        GENERAL_ASSET("General asset"),
        RESPONSIBILITY_ASSET("Responsibility asset");

        private final String name;

        Entity(@NotNull String name) {
            this.name = name;
        }
    }

    @Getter
    public enum Dropdown {
        ACCUSER("Accuser:"),
        ALLOCATION_REQUIRED("Allocation: *"),
        AMORTISATION_TYPE_REQUIRED("Amortisation type: *"),
        ASSET("Asset:"),
        ASSET_CLASSIFICATION_REQUIRED("Asset classification: *"),
        ASSET_CURRENCY_REQUIRED("Asset currency: *"),
        ASSET_REQUIRED("Asset: *"),
        ASSET_TYPE_REQUIRED("Asset type: *"),
        ATTORNEY_AT_LAW("Attorney at law:"),
        BANK("Bank:"),
        BUSINESS_PARTNER_REQUIRED("Business partner: *"),
        COMMISSIONED_BY_COMPANY("Commissioned by (Company):"),
        COMPANY_CATEGORY("Company category:"),
        COMPANY_CATEGORY_REQUIRED("Company category: *"),
        COMPANY_CLASSIFICATION("Company classification:"),
        COMPANY_CLASSIFICATION_REQUIRED("Company classification: *"),
        COMPANY_REQUIRED("Company: *"),
        CONTACT("Contact:"),
        CONTACT_AM("Contact AM:"),
        CONTACT_PM("Contact PM:"),
        CONTRACT_PARTNER_REQUIRED("Contract Partner: *"),
        CONTRACT_TYPE_REQUIRED("Contract type: *"),
        COUNTRY_REQUIRED("Country: *"),
        COURT("Court:"),
        CURRENCY_COMPANY_REQUIRED("Currency Company: *"),
        CURRENCY_REQUIRED("Currency: *"),
        DEBITORS_REQUIRED("Debitors: *"),
        DEBIT_POSITION_TYPE_REQUIRED("Debit position type: *"),
        DEFENDANT("Defendant:"),
        FEDERAL_STATE_REQUIRED("Federal state: *"),
        FUNDS_CATEGORY_REQUIRED("Funds category: *"),
        FUND_PORTFOLIO_MANDATE_REQUIRED("Fund | Portfolio | Mandate: *"),
        FUND_TYPE_REQUIRED("Fund type: *"),
        INDEX_FREE_TYPE("Index-free type:"),
        INDEX_SERIES_REQUIRED("Index series: *"),
        INSURANCE("Insurance:"),
        INTEREST_BASE_REQUIRED("Interest base: *"),
        INTERNAL_PAYEE("Internal Payee:"),
        LAND_AND_BUILDING_REQUIRED("Land & Building: *"),
        LAW_OFFICE("Law office:"),
        LEASE_CONTRACT_REQUIRED("Lease Contract: *"),
        LEASE_REQUIRED("Lease: *"),
        LEGAL_FORM("Legal form:"),
        LOAN_CONTINGENT_REQUIRED("Loan contingent: *"),
        LOAN_REQUIRED("Loan: *"),
        MAIN_CONTACT("Main contact:"),
        MEASURE_TYPE_REQUIRED("Measure type: *"),
        OPTION_HOLDER_REQUIRED("Option holder: *"),
        PAYMENT_INTERVAL_REQUIRED("Payment interval: *"),
        PORTFOLIO_REQUIRED("Portfolio: *"),
        REAL_ESTATE_APPRAISAL_TYPE_REQUIRED("Real estate appraisal type: *"),
        REFERENCE("Reference:"),
        RENTAL_UNIT_REQUIRED("Rental unit: *"),
        ROLE_REQUIRED("Role: *"),
        SCORING_CLUSTER_1("Scoring cluster 1 (type of use):"),
        STATUS("Status:"),
        TIME_SPAN_OF_VALUES_REQUIRED("Time span of values: *"),
        TYPE("Type:"),
        TYPE_OF_CANCELLATION_REQUIRED("Type of cancellation: *"),
        TYPE_OF_CHECK("Type of check:"),
        TYPE_OF_LOAN_REQUIRED("Type of loan: *"),
        TYPE_OF_TERMINATION_OF_PROCESSING("Type of termination of proceedings:"),
        TYPE_OF_USE_REQUIRED("Type of use: *"),
        UNIT_REQUIRED("Unit: *"),
        VAT_RATE("VAT rate:"),
        RESPONSIBILITY("Responsibility: *");

        private final String caption;

        Dropdown(@NotNull String caption) {
            this.caption = caption;
        }
    }

    @Getter
    public enum Input {
        ACCOUNT_DESCRIPTION_REQUIRED("Account description: *"),
        ACCOUNT_HOLDER_REQUIRED("Account holder: *"),
        ACCOUNT_ID_REQUIRED("AccountID: *"),
        ACCOUNT_NUMBER_REQUIRED("Account number: *"),
        ACQ_ECONOMIC_TRANSITION_DATE_REQUIRED("Acq. economic transition date: *"),
        ADAPTION_OFFSET_IN_MONTHS("Adaption offset in months:"),
        AMOUNT_IN_DISPUTE("Amount in dispute:"),
        APPORTIONMENT_RATE("Apportionment rate (in %):"),
        ASSET_ID_REQUIRED("Asset ID: *"),
        BUILDING_ID_REQUIRED("Building ID: *"),
        CITY_REQUIRED("City: *"),
        COMMENT("Comment:"),
        COMPANY_CODE_REQUIRED("Company code: *"),
        COMPANY_ID_REQUIRED("Company ID: *"),
        CONDITION_ID_REQUIRED("Condition ID: *"),
        CONDITION_VALID_FROM_REQUIRED("Condition valid from: *"),
        CONTRACT_ID_REQUIRED("Contract ID: *"),
        COST_OF_CAPITAL_INCREASE("Cost of capital increase in % (AG):"),
        COST_RISK("Cost risk in %:"),
        DATE_OF_APPRAISAL_REQUIRED("Date of appraisal: *"),
        DATE_OF_LAST_INDEXING("Date of last indexing:"),
        DATE_OF_THE_FIRST_INDEX_REFERENCE("Date of the first index reference:"),
        DATE_REQUIRED("Date: *"),
        DEADLINE_REQUIRED("Deadline: *"),
        DEBITORS_REQUIRED("Debitors: *"),
        DESCRIPTION_REQUIRED("Description: *"),
        DESIGNATION_REQUIRED("Designation: *"),
        DRAWDOWN_REMAIN_DEBT_REQUIRED("Drawdown (Remaining debt): *"),
        END("End:"),
        END_OF_INDEX_AGREEMENT("End of Index agreement:"),
        END_REQUIRED("End: *"),
        EXPECTED_COSTS("Expected costs:"),
        FILE_NUMBER("File number:"),
        FIXED_INTEREST_RATE("Fixed interest rate (in %):"),
        FIXED_MONTH_OF_ADJUSTMENT("Fixed month of adjustment:"),
        FIXED_VALUE("Fixed value:"),
        HOUSE_NUMBER_REQUIRED("House number: *"),
        IDENT_FIELD("Ident-Field: *"),
        ID_REQUIRED("ID: *"),
        INDEX_FREE_MONTHS("Index-free months:"),
        INDEX_VALUE_LAST_ADJUSTMENT("Index value last adjustment:"),
        INSURANCE_CLAIM_AMOUNT("Insurance claim amount:"),
        INSURANCE_CLAIM_DESIGNATION_REQUIRED("Insurance claim designation: *"),
        ISSUE_PRICE("Issue Price:"),
        LATITUDE_REQUIRED("Latitude: *"),
        LEASE_CONTRACT_REQUIRED("Lease Contract: *"),
        LINE_OF_CREDIT_REQUIRED("Line of credit: *"),
        LITIGATION_RISK("Litigation risk in %:"),
        LOAN_ID_REQUIRED("Loan ID: *"),
        LONGITUDE_REQUIRED("Longitude: *"),
        LOWER_LIMIT_FLOOR("Lower limit (floor):"),
        MAP_SEARCH_REQUIRED("Map Search: *"),
        MARGIN_RATE("Margin rate (in %):"),
        MARKET_VALUE_REQUIRED("Market value: *"),
        MAXIMUM_INCREASE("Maximum increase (%):"),
        MAXIMUM_INCREASE_CURRENCY("Maximum increase (currency):"),
        MEASURE_VALUE_REQUIRED("Measure value: *"),
        MINIMUM_ADJUSTMENT_FREQUENCY_AFTER_INDEXING("Minimum adjustment frequency after indexing:"),
        MINIMUM_INCREASE("Minimum increase (%):"),
        MINIMUM_INCREASE_CURRENCY("Minimum increase (currency):"),
        MULTIPLIER_ON_REFERENCE("Multiplier on reference:"),
        NOTICE_PERIOD_IN_MONTHS_REQUIRED("Notice period (in months): *"),
        NUMBER_OF_PERIODS_REQUIRED("Number of periods: *"),
        NUMBER_OF_REMAINING_AGREEMENTS("Number of remaining adjustments:"),
        NUMBER_OF_SHARES("Number of shares: *"),
        ORIGINAL_LOAN_AMOUNT("Original loan amount:"),
        PORTFOLIO_ID_REQUIRED("Portfolio ID: *"),
        POSTAL_CODE_REQUIRED("Postal code: *"),
        PROCESS_ID_REQUIRED("Process ID: *"),
        RENTAL_UNIT_REQUIRED("Rental unit: *"),
        REPORTING_DAY("Reporting day:"),
        REVIEW_FREQUENCY_IN_MONTHS("Review frequency in months:"),
        SHAREPRICE_REQUIRED("Shareprice: *"),
        SHORT_DESIGNATION("Short designation:"),
        SHORT_DESIGNATION_REQUIRED("Short designation: *"),
        START_FISCAL_YEAR_REQUIRED("Start fiscal year (FY): *"),
        START_LEGAL_CASE("Start legal case :"),
        START_REQUIRED("Start: *"),
        STREET_REQUIRED("Street: *"),
        TENANT_ID_REQUIRED("TenantID: *"),
        TERMINATION_DATE_OF_PROCESSING("Termination date of proceedings:"),
        THRESHOLD("Threshold:"),
        TRANSFER_OF_INDEX_INCREASE("Transfer of index increase:"),
        UNIT_ID_REQUIRED("Unit ID: *"),
        UNIT_START_REQUIRED("Unit start: *"),
        UPPER_LIMIT("Upper limit (cap):"),
        VALUE_REQUIRED("Value: *"),
        VALID_FROM("Valid from: *"),
        VALID_UNTIL("Valid until: *"),
        CONTACT_PERSON("Contact person: *"),
        DEPUTY("Deputy:");

        private final String caption;

        Input(@NotNull String caption) {
            this.caption = caption;
        }
    }

    @Getter
    public enum RadioButton {
        VACANCY_CONTRACT("Vacancy contract: *"),
        TEMPORARY_CONTRACT("Temporary contract: *");

        private final String caption;

        RadioButton(@NotNull String caption) {
            this.caption = caption;
        }
    }
}
