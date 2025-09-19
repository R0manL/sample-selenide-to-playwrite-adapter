package com.superit.smart.qa.api.smartbox.services;

import com.superit.smart.qa.api.smartbox.pojo.*;
import com.superit.smart.qa.api.smartbox.pojo.property.PropertyEntity;
import com.superit.smart.qa.core.enums.User;
import com.superit.smart.qa.ui.smartbox.enums.EmptyFieldText;
import com.superit.smart.qa.utils.enums.DatePattern;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.superit.smart.qa.api.smartbox.enums.UserModule.MASTER_DATA;
import static com.superit.smart.qa.api.smartbox.services.BBApiBase.setupBBRequestSpecFor;
import static com.superit.smart.qa.utils.DateUtils.convertDateToStringInFormat;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class RealEstateEntityService {

    private static final String CREATE_PROPERTY_PATH = "odataservice/odata/wizard";
    private static final String GET_UNITS_LIST = "odataservice/odata/unitsset";
    private static final String GET_LEASES_LIST = "odataservice/odata/leasesset";
    private static final String CONTR_PARTNERS_LIST = "odataservice/odata/contrpartnersset";
    private static final String BUILDINGS_LIST = "odataservice/odata/landbuildingsset";
    private static final String KEY_VALUE_SET_LIST = "odataservice/odata/keyvaluesset";
    private static final String ASSETS_LIST = "odataservice/odata/propertiesset";
    private static final String PORTFOLIOS_LIST = "odataservice/odata/portfolioset";
    private static final String CONTRACT_CONDITIONS_LIST = "odataservice/odata/contractconditionsset";
    private static final String DELETE_BATCH_PATH = "odataservice/odata/$batch";
    private static final String GET_CONTRACTS_LIST = "odataservice/odata/contractsset";
    private static final String GET_BANK_ACCOUNTS_LIST = "odataservice/odata/bankaccountsset";
    private static final String LINKS_LIST = "odataservice/odata/propertiesset/TreeServiceLinkPreview";
    private static final String KEY_VALUE_RESPONSIBILITY_SET_LIST = "odataservice/odata/responsibilitiesset";
    private static final String CREDITOR_LIST = "odataservice/odata/addressesset";
    private static final String DEBIT_LIST = "/odataservice/odata/debitsset";
    private static final String MEASURE_LIST = "/odataservice/odata/unitmeasuresset";
    private static final String OPTION_TERMINATION_RIGHT_LIST = "/odataservice/odata/leasebreakoptionsset";
    private static final String DEPOSIT_LIST = "/odataservice/odata/depositsset";
    private static final String CONDITION_LIST = "/odataservice/odata/termsset";
    private static final String ASSET_MANAGEMENT_ACTIVITY_LIST = "/odataservice/odata/assetactivitiesset";
    private static final String GET_LOAN_LIST = "odataservice/odata/loanset";
    private static final String STRATEGY_LIST = "/odataservice/odata/fundsstrategiesset";
    private static final String RESPONSIBILITY_LIST = "/odataservice/odata/responsibilitiesset";
    private static final String COMPANIES_LIST = "odataservice/odata/companiesset";
    private static final String SHAREHOLDING_LIST = "/odataservice/odata/companyownershipset";
    private static final String CONTRACT_CONDITION_LIST = "/odataservice/odata/contractconditionsset";
    private static final String INDEX_AGREEMENTS_LIST = "/odataservice/odata/leaseindicesset";
    private static final String MARKET_VALUE_APPRAISALS_LIST = "/odataservice/odata/valuations2set";
    private static final String SYNDICATED_LOAN_LIST = "/odataservice/odata/zsynloansset";

    private static final String EXPAND_PARAM_KEY = "$expand";
    private static final String SELECT_PARAM_KEY = "$select";
    private static final String MENUSAASID_PARAM_KEY = "menusaasid";
    private static final String ORDERBY_PARAM_KEY = "$orderby";
    private static final String FILTER_PARAM_KEY = "$filter";
    private static final String RECORD_STATE = "RecordState";

    private static final String ISCHILDENTITIES_PARAM_KEY = "ischildentities";
    private static final String COLUMNID_PARAM_KEY = "columnId";

    private static final String ORDERBY_PARAM_DEFAULT_VALUE = "id,designation_en";
    private static final String SELECT_PARAM_DEFAULT_VALUE = "lid,id,designation_en";
    private static final String ORDER_BY_ID_ASC_DESIGNATIONSHORT_ASC = "id asc,designationshort asc";

    private static final String FALSE = "false";
    private static final String TRUE = "true";
    private static final String LID_ID_DESIGNATION_VALUE = "lid,id,designationshort";

    private static final String DELETE_ALL = "DeleteAll";

    private RealEstateEntityService() {
    }

    public static OdataResponse createEntity(PropertyEntity propertyEntity, User user) {
        log.info("API: Creating Property: '{}' for user: '{}'.", propertyEntity, user);
        return setupBBRequestSpecFor(user)
                .basePath(CREATE_PROPERTY_PATH)
                .body(propertyEntity)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataResponse.class);
    }

    public static OdataContextValueAsset getAssetsOf(User user) {
        log.info("API: Get assets of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(ASSETS_LIST)
                .queryParam(ORDERBY_PARAM_KEY, "designationshort asc,id asc")
                .queryParam(SELECT_PARAM_KEY, "lid,designationshort,id")
                .queryParam(MENUSAASID_PARAM_KEY, "003")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueAsset.class);
    }

    public static OdataContextValueAsset getGeneralAssetsOf(User user) {
        log.info("API: Get assets of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(ASSETS_LIST)
                .queryParam(ORDERBY_PARAM_KEY, "designationshort asc,id asc")
                .queryParam(SELECT_PARAM_KEY, "lid,designationshort,id")
                .queryParam(MENUSAASID_PARAM_KEY, "184")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueAsset.class);
    }

    public static void deleteAsset(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete asset with lid '{}' of user '{}'", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(ASSETS_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, TRUE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void deleteBuilding(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete Building with lid '{}' for user: '{}'.", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(BUILDINGS_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, TRUE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OdataContextValuePortfolio getPortfolioOf(User user) {
        log.info("API: Get portfolios of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(PORTFOLIOS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "sessiondetaillidLink($select=lid,designationplanningsession;$expand=sessionLink($select=lid,id,name))")
                .queryParam(ORDERBY_PARAM_KEY, ORDER_BY_ID_ASC_DESIGNATIONSHORT_ASC)
                .queryParam(SELECT_PARAM_KEY, "lid,id,designationshort,designation,sessiondetaillid")
                .queryParam(MENUSAASID_PARAM_KEY, "001")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValuePortfolio.class);
    }

    public static void deletePortfolio(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete portfolio with id '{}' of user '{}'", lid, user);
        OdataDeleteRequest request = OdataDeleteRequest.builder()
                .requests(Collections.singletonList(DeleteRequest.builder()
                        .id(lid)
                        .method("DELETE")
                        .url(String.format("portfolioset(%s)?ischildentities=false", lid))
                        .headers(PortfolioDeleteRequestHeaders.builder()
                                .contentType("APPLICATION/JSON")
                                .build())
                        .build()))
                .build();

        setupBBRequestSpecFor(user)
                .basePath(DELETE_BATCH_PATH)
                .queryParam(ISCHILDENTITIES_PARAM_KEY, FALSE)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OdataContextValueContractCondition getContractConditionsOf(User user) {
        log.info("API: Get Contract Conditions of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(CONTRACT_CONDITIONS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "datalevelLink($select=levelLid,identificationEnglish,levelTableName),budgetgroupKeyValue($select=lid,id,designation_en),indexseriesLink($select=lid,id,designation),contractsLink($select=lid,id,designationshort;$expand=contracttypeKeyValue($select=lid)),paymentintervalLink($select=lid,designation_en),paymentmodeKeyValue($select=lid,id,designation_en),referencevalueLink($select=lid,zkpiidentification_en),stateKeyValue($select=lid,id,designation_en),thresholdintervalbasedKeyValue($select=lid,id,designation_en),timereferenceKeyValue($select=lid,id,designation_en),sessiondetaillidLink($select=lid,designationplanningsession;$expand=sessionLink($select=lid,id,name))")
                .queryParam(SELECT_PARAM_KEY, "lid,datalevelreference,acceptancerenewaloption,apportionmentrate,budgetgroup,cashflowfactor,conditionid")
                .queryParam(MENUSAASID_PARAM_KEY, "010")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueContractCondition.class);
    }

    public static void deleteContractCondition(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete Contract Condition with id '{}' of user '{}'", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(CONTRACT_CONDITIONS_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, FALSE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void deleteBankAccount(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete bank account with lid '{}' of user '{}'", lid, user);
        OdataDeleteRequest request = OdataDeleteRequest.builder()
                .requests(Collections.singletonList(DeleteRequest.builder()
                        .id(lid)
                        .method("DELETE")
                        .url(String.format("bankaccountsset(%s)?ischildentities=false", lid))
                        .headers(PortfolioDeleteRequestHeaders.builder()
                                .contentType("APPLICATION/JSON")
                                .build())
                        .build()))
                .build();

        setupBBRequestSpecFor(user)
                .basePath(DELETE_BATCH_PATH)
                .queryParam(ISCHILDENTITIES_PARAM_KEY, FALSE)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OdataContextValueLease getLeasesList(@NotNull User user) {
        return getLeasesList(user, false);
    }

    public static OdataContextValueLease getLeasesList(@NotNull User user, boolean isMDSpecified) {
        log.info("API: Get all Leases for user: '{}'.", user);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(GET_LEASES_LIST)
                .queryParam(EXPAND_PARAM_KEY, "tenantLink($select=lid,id,designationshort)")
                .queryParam(SELECT_PARAM_KEY, "lid,id,tenant")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(ORDERBY_PARAM_KEY, "id asc,tenantLink/id asc,tenantLink/designationshort asc");

        if (isMDSpecified) reqSpec.header("smartit-usermodule", MASTER_DATA.toString());

        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueLease.class);
    }

    public static OdataContextValueLoan getLoanList(@NotNull User user) {
        return getLoanList(false, user);
    }

    public static OdataContextValueLoan getLoanList(boolean idMDSession, @NotNull User user) {
        log.info("API: Get all planning loans for user: '{}'.", user);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(GET_LOAN_LIST)
                .queryParam(EXPAND_PARAM_KEY, "sessiondetaillidLink($select=lid,delete,sessionsbezen)")
                .queryParam(SELECT_PARAM_KEY, "lid,id,designation,sessiondetaillid")
                .queryParam(MENUSAASID_PARAM_KEY, "013")
                .queryParam(ORDERBY_PARAM_KEY, "id asc,designation asc");
        if (idMDSession) reqSpec.header("smartit-usermodule", "smart-data");

        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueLoan.class);
    }

    public static OdataContextValueBankAccount getBankAccountList(@NotNull User user) {
        log.info("API: Get all Leases for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(GET_BANK_ACCOUNTS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "bankLink($select=lid,id,designationshort),propertiesLink($select=lid,id,designationshort),typeofbankaccountKeyValue($select=lid,id,designation_en),accountcurrencyKeyValue($select=lid,id,designation_en)")
                .queryParam(SELECT_PARAM_KEY, "bank,lparentid,accountnumber,accountdescription,accountholder,accountidonlyaccount,iban,typeofbankaccount,accountcurrency,lockflag,extinct,accountvalidfrom,accountvalidto,finaccpurpose,lid")
                .queryParam(MENUSAASID_PARAM_KEY, "345")
                .queryParam(ORDERBY_PARAM_KEY, "bankLink/id asc,bankLink/designationshort asc,propertiesLink/id asc,propertiesLink/designationshort asc")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueBankAccount.class);
    }

    public static void deleteLease(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete lease with lid '{}' for user: '{}'.", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_LEASES_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, TRUE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void deleteUnit(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete unit with lid '{}' for user: '{}'.", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_UNITS_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, TRUE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void updateDefineEndDateEndWidgetLease(@NotNull String lid, @NotNull LocalDate date, @NotNull User user) {
        String dateString = convertDateToStringInFormat(date, DatePattern.API_ENTITY_DATE_FORMAT);
        log.info("API: Update lease with lid '{}' end date with date '{}' for user: '{}'.", lid, date, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_LEASES_LIST + "(%s)", lid))
                .param("widgetName", "End")
                .param("widgetType", "Widget")
                .body("{'definiteenddate': '" + dateString + "'}")
                .when()
                .patch()
                .then()
                .statusCode(HTTP_NO_CONTENT);
    }

    public static EndWidgetLease getEndWidgetLease(@NotNull String lid, @NotNull User user) {
        log.info("API: Get End Widget Data of lease with lid '{}' for user: '{}'.", lid, user);
        return setupBBRequestSpecFor(user)
                .basePath(String.format(GET_LEASES_LIST + "(%s)", lid))
                .queryParam(EXPAND_PARAM_KEY, "terminationruleKeyValue($select=lid,id,designation_en)")
                .queryParam(SELECT_PARAM_KEY, "lid,definiteenddate,endfixedduration,firstleaseend,nextdateofterminationtenant,nextendofcontract,terminationrule,timelimit,transitionintounlimitedcontract")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(EndWidgetLease.class);
    }

    public static OdataContextValueUnit getUnits(User user) {
       return getUnits(user, false);
    }


    public static OdataContextValueUnit getUnits(User user, boolean isMDSpecified) {
        log.info("API: Get units of user '{}'", user);
         RequestSpecification
                 reqSpec1 = setupBBRequestSpecFor(user)
                .basePath(GET_UNITS_LIST)
                .queryParam(ORDERBY_PARAM_KEY, "id,designationshort")
                .queryParam(SELECT_PARAM_KEY, LID_ID_DESIGNATION_VALUE)
                .queryParam(MENUSAASID_PARAM_KEY, "005");

        if (isMDSpecified) reqSpec1.header("smartit-usermodule", MASTER_DATA.toString());

        return
                reqSpec1.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueUnit.class);
    }

    public static OdataContextValueContrPartner getContrPartners(@NotNull User user) {
        log.info("API: Get Contract Partners for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(CONTR_PARTNERS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "personLink($select=designationshort)")
                .queryParam(FILTER_PARAM_KEY, "delete eq false")
                .queryParam(SELECT_PARAM_KEY, "lid, id")
                .queryParam(COLUMNID_PARAM_KEY, "8496")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueContrPartner.class);
    }

    public static OdataContextValueBuildings getBuildings(@NotNull User user) {
        log.info("API: Get Buildings for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(BUILDINGS_LIST)
                .queryParam(ORDERBY_PARAM_KEY, ORDER_BY_ID_ASC_DESIGNATIONSHORT_ASC)
                .queryParam(SELECT_PARAM_KEY, LID_ID_DESIGNATION_VALUE)
                .queryParam(MENUSAASID_PARAM_KEY, "004")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueBuildings.class);
    }

    public static OdataContextValueTypeOfUse getTypeOfUse(@NotNull User user) {
        log.info("API: Get Buildings for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(KEY_VALUE_SET_LIST)
                .queryParam(ORDERBY_PARAM_KEY, ORDERBY_PARAM_DEFAULT_VALUE)
                .queryParam(FILTER_PARAM_KEY, "delete eq false and keyheadsLink/designation eq 'Flaechenarten'")
                .queryParam(SELECT_PARAM_KEY, SELECT_PARAM_DEFAULT_VALUE)
                .queryParam(COLUMNID_PARAM_KEY, "15797")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueTypeOfUse.class);
    }

    public static OdataContextValueContract getContractsOf(@NotNull User user) {
        log.info("API: Get all Contracts for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(GET_CONTRACTS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "contracttypeKeyValue($select=lid,id,designation_en),sessiondetaillidLink($select=lid,designationplanningsession)")
                .queryParam(SELECT_PARAM_KEY, "lid,id,designationshort,contracttype,sessiondetaillid")
                .queryParam(MENUSAASID_PARAM_KEY, "010")
                .queryParam(ORDERBY_PARAM_KEY, ORDER_BY_ID_ASC_DESIGNATIONSHORT_ASC)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueContract.class);
    }

    public static OdataContextValueFee getFeesOf(@NotNull User user) {
        log.info("API: Get all Fees for user: '{}'.", user);
        return setupBBRequestSpecFor(user)
                .basePath(GET_CONTRACTS_LIST)
                .queryParam(EXPAND_PARAM_KEY, "contracttypeKeyValue($select=lid,id,designation_en),sessiondetaillidLink($select=lid)")
                .queryParam(SELECT_PARAM_KEY, "lid,id,designationshort,contracttype,sessiondetaillid")
                .queryParam(MENUSAASID_PARAM_KEY, "407")
                .queryParam(ORDERBY_PARAM_KEY, ORDER_BY_ID_ASC_DESIGNATIONSHORT_ASC)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueFee.class);
    }

    public static OdataContextValueVatRate getVatRatesOf(User user) {
        log.info("API: Get VAT rates of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(KEY_VALUE_SET_LIST)
                .queryParam(FILTER_PARAM_KEY, "delete eq false and keyheadsLink/designation eq 'VAT rate'")
                .queryParam(ORDERBY_PARAM_KEY, ORDERBY_PARAM_DEFAULT_VALUE)
                .queryParam(SELECT_PARAM_KEY, SELECT_PARAM_DEFAULT_VALUE)
                .queryParam(COLUMNID_PARAM_KEY, 30184)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueVatRate.class);
    }

    public static OdataContextValueCurrency geCurrencyOf(User user) {
        log.info("API: Get Currency of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(KEY_VALUE_SET_LIST)
                .queryParam(FILTER_PARAM_KEY, "delete eq false and keyheadsLink/designation eq 'Waehrungen'")
                .queryParam(ORDERBY_PARAM_KEY, ORDERBY_PARAM_DEFAULT_VALUE)
                .queryParam(SELECT_PARAM_KEY, SELECT_PARAM_DEFAULT_VALUE)
                .queryParam(COLUMNID_PARAM_KEY, 30181)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueCurrency.class);
    }

    public static OdataContextValueCompany getCompanies(User user) {
        log.info("API: Get companies of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(COMPANIES_LIST)
                .queryParam(ORDERBY_PARAM_KEY, "id,designationshort")
                .queryParam(SELECT_PARAM_KEY, LID_ID_DESIGNATION_VALUE)
                .queryParam(MENUSAASID_PARAM_KEY, "002")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueCompany.class);
    }

    public static OdataContextValueValuation getMarketValueAppraisals(int lid, @NotNull User user) {
        log.info("API: Get Market Value Appraisals of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(MARKET_VALUE_APPRAISALS_LIST)
                .queryParam(ORDERBY_PARAM_KEY, "propertiesLink/id asc,propertiesLink/designationshort asc,dateofappraisalid desc")
                .queryParam(SELECT_PARAM_KEY, "lid,lparentid,rebasetype1")
                .queryParam(MENUSAASID_PARAM_KEY, "172")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + lid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueValuation.class);
    }

    public static void deleteContract(@NotNull String lid, @NotNull User user) {
        log.info("API: Delete contract with lid '{}' for user: '{}'.", lid, user);
        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_CONTRACTS_LIST + "(%s)", lid))
                .param(ISCHILDENTITIES_PARAM_KEY, TRUE)
                .when()
                .delete()
                .then()
                .statusCode(HTTP_OK);
    }

    public static Responsibilities getAssetResponsibilitiesBy(int lid, @NotNull User user) {
        log.info("API: Get Responsibility of asset with lid '{}'", lid);
        return setupBBRequestSpecFor(user)
                .basePath(KEY_VALUE_RESPONSIBILITY_SET_LIST)
                .queryParam(EXPAND_PARAM_KEY, "businesspartnerLink($select=lid,id,designationshort),replcementLink($select=lid,id,designationshort),typeKeyValue($select=lid,id,designation_en)")
                .queryParam(SELECT_PARAM_KEY, "lid,businesspartner,replcement,type,validfrom,validto")
                .queryParam(MENUSAASID_PARAM_KEY, "003")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + lid)
                .queryParam("menuentityids", lid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(Responsibilities.class);
    }

    public static Links getAssetLinksBy(int lid, @NotNull User user) {
        return setupBBRequestSpecFor(user)
                .basePath(LINKS_LIST)
                .body("{menuid: \"003\", ids: [" + lid + "]}")
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(Links.class);
    }

    public static OdataContextValueCreditor getCreditorsOf(User user) {
        log.info("API: Get Creditors of user '{}'", user);
        return setupBBRequestSpecFor(user)
                .basePath(CREDITOR_LIST)
                .queryParam(FILTER_PARAM_KEY, "delete eq false")
                .queryParam(ORDERBY_PARAM_KEY, "id,designationshort")
                .queryParam(SELECT_PARAM_KEY, LID_ID_DESIGNATION_VALUE)
                .queryParam(COLUMNID_PARAM_KEY, 36107)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueCreditor.class);
    }

    public static DebitSet getDebitsBy(int parentLid, @NotNull User user) {
        log.info("API: Get Debits list of unit with lid '{}'", parentLid);
        return getDebitsBy(parentLid, false, user);
    }

    public static DebitSet getDebitsBy(int parentLid, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get Debits list of unit with lid '{}'", parentLid);
        RequestSpecification reqSpec =  setupBBRequestSpecFor(user)
                .basePath(DEBIT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "005")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", MASTER_DATA.toString());

        return  reqSpec .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(DebitSet.class);
    }

    public static DebitSet getSoftDeletedDebitsBy(int parentLid, @NotNull User user) {
        return getSoftDeletedDebitsBy(parentLid, false, user);
    }

    public static DebitSet getSoftDeletedDebitsBy(int parentLid, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get soft deleted Debits list of unit with lid '{}'", parentLid);
        RequestSpecification reqSpec =  setupBBRequestSpecFor(user)
                .basePath(DEBIT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "996")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        return reqSpec
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(DebitSet.class);
    }

    public static void hardDeleteDebitItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        hardDeleteDebitItem (lidsToDelete, false, user);
    }

    public static void hardDeleteDebitItem(@NotNull List<Integer> lidsToDelete, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Delete Debit items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("005")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        RequestSpecification reqSpec = setupBBRequestSpecFor(user);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        reqSpec
                .basePath(String.format(DEBIT_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static MeasureSet getMeasuresBy(int parentLid, @NotNull User user) {
        log.info("API: Get Measures list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(MEASURE_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "005")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(MeasureSet.class);
    }

    public static MeasureSet getSoftDeletedMeasuresBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Measures list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(MEASURE_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "997")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(MeasureSet.class);
    }

    public static void hardDeleteMeasureItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete measure items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("005")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(MEASURE_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OptionSet getOptionBy(int parentLid, @NotNull User user) {
        log.info("API: Get Option list of unit with lid '{}'", parentLid);
        return getOptionBy(parentLid, false, user);
    }

    public static OptionSet getOptionBy(int parentLid, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get Option list of unit with lid '{}'", parentLid);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(OPTION_TERMINATION_RIGHT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid + " and typeofcancellationKeyValue/id eq 'T' or typeofcancellationKeyValue/id eq 'Z' or typeofcancellationKeyValue/id eq 'O'")
                .queryParam("menuentityids", parentLid);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OptionSet.class);
    }

    public static OptionSet getSoftDeletedOptionTerminationRightBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Option list of unit with lid '{}'", parentLid);
        return getSoftDeletedOptionTerminationRightBy(parentLid, false, user);
    }

    public static OptionSet getSoftDeletedOptionTerminationRightBy(int parentLid, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get soft deleted Option list of unit with lid '{}'", parentLid);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(OPTION_TERMINATION_RIGHT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "999")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");
        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OptionSet.class);
    }

    public static void hardDeleteOptionTerminationRightItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        hardDeleteOptionTerminationRightItem(lidsToDelete, false, user);
    }

    public static void hardDeleteOptionTerminationRightItem(@NotNull List<Integer> lidsToDelete, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Delete option items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("006")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        RequestSpecification reqSpec = setupBBRequestSpecFor(user);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        reqSpec
                .basePath(String.format(OPTION_TERMINATION_RIGHT_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OptionSet getTerminationRightBy(int parentLid, @NotNull User user) {
        log.info("API: Get Termination Right list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(OPTION_TERMINATION_RIGHT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid + " and typeofcancellationKeyValue/id eq '1'")
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OptionSet.class);
    }

    public static OptionSet getDepositsBy(int parentLid, @NotNull User user) {
        log.info("API: Get Deposit list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(DEPOSIT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OptionSet.class);
    }

    public static OptionSet getSoftDeletedDepositBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Deposit list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(DEPOSIT_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "313")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OptionSet.class);
    }

    public static void hardDeleteDepositItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Deposit items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("006")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(DEPOSIT_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static ConditionSet getConditionsBy(int parentLid, @NotNull User user) {
        log.info("API: Get Condition list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "013")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ConditionSet.class);
    }

    public static ConditionSet getSoftDeletedConditionBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Condition list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "138")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ConditionSet.class);
    }

    public static void hardDeleteConditionItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Condition items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("013")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(CONDITION_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static AssetManagementActivitySet getAssetManagementActivitiesBy(int parentLid, @NotNull User user) {
        log.info("API: Get Asset Management Activities list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(ASSET_MANAGEMENT_ACTIVITY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "003")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(AssetManagementActivitySet.class);
    }

    public static AssetManagementActivitySet getSoftDeletedAssetManagementActivitiesBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Asset Management Activities list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(ASSET_MANAGEMENT_ACTIVITY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "995")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(AssetManagementActivitySet.class);
    }

    public static void hardDeleteAssetManagementActivitiesItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Asset Management Activities items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("003")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(ASSET_MANAGEMENT_ACTIVITY_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static StrategiesSet getStrategiesBy(int parentLid, @NotNull User user) {
        log.info("API: Get Strategy list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(STRATEGY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "001")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(StrategiesSet.class);
    }

    public static SyndicatedLoansSet getSyndicatedLoansBy(int parentLid, @NotNull User user) {
        log.info("API: Get Syndicated Loans list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(SYNDICATED_LOAN_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "013")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(SyndicatedLoansSet.class);
    }

    public static SyndicatedLoansSet getSoftDeletedSyndicatedLoanBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Syndicated Loan list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(SYNDICATED_LOAN_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "013")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(SyndicatedLoansSet.class);
    }

    public static StrategiesSet getSoftDeletedStrategyBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Strategy list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(STRATEGY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "138")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(StrategiesSet.class);
    }

    public static void hardDeleteStrategyItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Strategy items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("001")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(STRATEGY_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteSyndicatedLoanItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Syndicated Loan items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("013")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(SYNDICATED_LOAN_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static ResponsibilitySet getResponsibilityBy(int parentLid, @NotNull User user) {
        log.info("API: Get Responsibility list of asset with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(RESPONSIBILITY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "003")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ResponsibilitySet.class);
    }

    public static ResponsibilitySet getSoftDeletedResponsibilityBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Responsibility list of asset with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(RESPONSIBILITY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "991")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ResponsibilitySet.class);
    }

    public static void hardDeleteResponsibilityItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Responsibility items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("003")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(RESPONSIBILITY_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static MeasureSet getShareholdingsBy(int parentLid, @NotNull User user) {
        log.info("API: Get Shareholding list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(SHAREHOLDING_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "002")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(MeasureSet.class);
    }

    public static MeasureSet getSoftDeletedShareholdingsBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Shareholding list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(SHAREHOLDING_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "988")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(MeasureSet.class);
    }

    public static void hardDeleteShareholdingItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Shareholding items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("003")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(SHAREHOLDING_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static ContractConditionSet getContractConditionsByContractId(int parentLid, @NotNull User user) {
        log.info("API: Get Condition list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(CONTRACT_CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "010")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ContractConditionSet.class);
    }

    public static ContractConditionSet getContractConditions(@NotNull User user) {
        log.info("API: Get Contract Condition list");
        return setupBBRequestSpecFor(user)
                .basePath(CONTRACT_CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid,conditionid")
                .queryParam(MENUSAASID_PARAM_KEY, "010")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ContractConditionSet.class);
    }

    public static ContractConditionSet getSoftDeletedContractConditionByContractId(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted contract condition list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(CONTRACT_CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "138")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ContractConditionSet.class);
    }

    public static ContractConditionSet getSoftDeletedContractCondition(@NotNull User user) {
        log.info("API: Get soft deleted contract condition list");
        return setupBBRequestSpecFor(user)
                .basePath(CONTRACT_CONDITION_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid,conditionid")
                .queryParam(MENUSAASID_PARAM_KEY, "138")
                .queryParam(RECORD_STATE, "1")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ContractConditionSet.class);
    }

    public static void hardDeleteContractConditionItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete contract condition items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("010")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(CONTRACT_CONDITION_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static OdataContextValueAsset getSoftDeletedAssetsBy(String assetId, @NotNull User user) {
        log.info("API: Get soft deleted Asset with lid '{}'", assetId);
        return setupBBRequestSpecFor(user)
                .basePath(ASSETS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "003")
                .queryParam(SELECT_PARAM_KEY, "lid,designationshort,id")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueAsset.class);
    }

    public static OdataContextValueLease getSoftDeletedLeasesBy(String leaseId, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get soft deleted Leases with lid '{}'", leaseId);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(GET_LEASES_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(SELECT_PARAM_KEY, "lid,id");

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");
        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueLease.class);
    }

    public static OdataContextValueLoan getSoftDeletedLoansBy(String leaseId, @NotNull User user) {
        return getSoftDeletedLoansBy(leaseId, false, user);
    }

    public static OdataContextValueLoan getSoftDeletedLoansBy(String leaseId, boolean isMDSelected, @NotNull User user) {
        log.info("API: Get soft deleted Loans with lid '{}'", leaseId);
        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(GET_LOAN_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "013")
                .queryParam(SELECT_PARAM_KEY, "lid,id");
        if (isMDSelected) reqSpec.header("smartit-usermodule", "smart-data");
        return reqSpec.when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueLoan.class);
    }


    public static OdataContextValueContract getSoftDeletedContractBy(String leaseId, @NotNull User user) {
        log.info("API: Get soft deleted Contract with lid '{}'", leaseId);
        return setupBBRequestSpecFor(user)
                .basePath(GET_CONTRACTS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(EXPAND_PARAM_KEY, "contracttypeKeyValue($select=lid,id,designation_en)")
                .queryParam(SELECT_PARAM_KEY, "id,designationshort,contracttype,lid")
                .queryParam(MENUSAASID_PARAM_KEY, "010")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueContract.class);
    }

    public static OdataContextValueFee getSoftDeletedFeeBy(String feeId, @NotNull User user) {
        log.info("API: Get soft deleted Fee with lid '{}'", feeId);
        return setupBBRequestSpecFor(user)
                .basePath(GET_CONTRACTS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(EXPAND_PARAM_KEY, "contracttypeKeyValue($select=lid,id,designation_en)")
                .queryParam(SELECT_PARAM_KEY, "lid,id,designationshort,contracttype,sessiondetaillid")
                .queryParam(MENUSAASID_PARAM_KEY, "407")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueFee.class);
    }

    public static OdataContextValueUnit getSoftDeletedUnitsBy(String unitId, @NotNull User user) {
        return getSoftDeletedUnitsBy(unitId, false, user);
    }

    public static OdataContextValueUnit getSoftDeletedUnitsBy(String unitId, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Get soft deleted Units with lid '{}'", unitId);
        RequestSpecification reqSpec =  setupBBRequestSpecFor(user)
                .basePath(GET_UNITS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "005")
                .queryParam(SELECT_PARAM_KEY, "lid,id");

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        return reqSpec
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueUnit.class);
    }

    public static OdataContextValuePortfolio getSoftDeletedPortfoliosBy(String portfolioId, @NotNull User user) {
        log.info("API: Get soft deleted Asset with lid '{}'", portfolioId);
        return setupBBRequestSpecFor(user)
                .basePath(PORTFOLIOS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "001")
                .queryParam(SELECT_PARAM_KEY, "lid,designationshort,id")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValuePortfolio.class);
    }

    public static OdataContextValueCompany getSoftDeletedCompaniesBy(String companyId, @NotNull User user) {
        log.info("API: Get soft deleted Company with lid '{}'", companyId);
        return setupBBRequestSpecFor(user)
                .basePath(COMPANIES_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "002")
                .queryParam(SELECT_PARAM_KEY, "lid,designationshort,id")
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueCompany.class);
    }

    public static OdataContextValueValuation getSoftDeletedMarketValueAppraisalsBy(int assetId, @NotNull User user) {
        log.info("API: Get soft deleted Market Value Appraisals with asset lid '{}'", assetId);
        return setupBBRequestSpecFor(user)
                .basePath(MARKET_VALUE_APPRAISALS_LIST)
                .queryParam(RECORD_STATE, "1")
                .queryParam(MENUSAASID_PARAM_KEY, "172")
                .queryParam(ORDERBY_PARAM_KEY, "propertiesLink/id asc,propertiesLink/designationshort asc,dateofappraisalid desc")
                .queryParam(SELECT_PARAM_KEY, "lid,lparentid,rebasetype1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + assetId)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(OdataContextValueValuation.class);
    }

    public static void hardDeleteAssetItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete asset items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("003")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(ASSETS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteLeaseItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        hardDeleteLeaseItem(lidsToDelete, false, user);
    }

    public static void hardDeleteLeaseItem(@NotNull List<Integer> lidsToDelete, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Delete lease items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("006")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(String.format(GET_LEASES_LIST + "/" + DELETE_ALL));

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        reqSpec.body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteLoanItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        hardDeleteLoanItem(lidsToDelete, false, user);
    }

    public static void hardDeleteLoanItem(@NotNull List<Integer> lidsToDelete, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Delete loan items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("013")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        RequestSpecification reqSpec = setupBBRequestSpecFor(user)
                .basePath(String.format(GET_LOAN_LIST + "/" + DELETE_ALL));

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");
        reqSpec
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteFeesItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete fee items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("407")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_CONTRACTS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteContractsItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete opex items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("010")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(GET_CONTRACTS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteUnitItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        hardDeleteUnitItem(lidsToDelete, false, user);
    }

    public static void hardDeleteUnitItem(@NotNull List<Integer> lidsToDelete, boolean isMDSpecified, @NotNull User user) {
        log.info("API: Delete unit items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("005")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        RequestSpecification reqSpec = setupBBRequestSpecFor(user);

        if (isMDSpecified) reqSpec.header("smartit-usermodule", "smart-data");

        reqSpec
                .basePath(String.format(GET_UNITS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeletePortfolioItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete asset items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("001")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(PORTFOLIOS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteCompanyItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete company items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("002")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(COMPANIES_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static void hardDeleteMarketValueAppraisalsIdsItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete company items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("172")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(2)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(MARKET_VALUE_APPRAISALS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static IndexAgreementsSet getIndexAgreementsBy(int parentLid, @NotNull User user) {
        log.info("API: Get Index Agreement list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(INDEX_AGREEMENTS_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "006")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(IndexAgreementsSet.class);
    }

    public static IndexAgreementsSet getSoftDeletedIndexAgreementsBy(int parentLid, @NotNull User user) {
        log.info("API: Get soft deleted Index Agreement list of unit with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(INDEX_AGREEMENTS_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "998")
                .queryParam(RECORD_STATE, "1")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(IndexAgreementsSet.class);
    }

    public static void hardDeleteIndexAgreementsItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Index Agreement items with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("006")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(INDEX_AGREEMENTS_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    public static ResponsibilitySet getGeneralAssetResponsibilityBy(int parentLid, @NotNull User user) {
        log.info("API: Get Responsibility list of general asset with lid '{}'", parentLid);
        return setupBBRequestSpecFor(user)
                .basePath(RESPONSIBILITY_LIST)
                .queryParam(SELECT_PARAM_KEY, "lid")
                .queryParam(MENUSAASID_PARAM_KEY, "184")
                .queryParam(FILTER_PARAM_KEY, "lparentid eq " + parentLid)
                .queryParam("menuentityids", parentLid)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .as(ResponsibilitySet.class);
    }

    public static void hardDeleteGeneralAssetResponsibilityItem(@NotNull List<Integer> lidsToDelete, @NotNull User user) {
        log.info("API: Delete Responsibility items from general asset with lids '{}' of user '{}'", lidsToDelete, user);
        HardDeleteRequest hardDeleteRequest = HardDeleteRequest.builder()
                .menuId("184")
                .idsToDelete(lidsToDelete)
                .hardDeleteParam(1)
                .excludedLids(new ArrayList<>())
                .navigationBarFilter(
                        HardDeleteRequest.NavigationBarFilter
                                .builder()
                                .searchValue(EmptyFieldText.EMPTY.getText())
                                .propertyNames(new ArrayList<>())
                                .build())
                .build();

        setupBBRequestSpecFor(user)
                .basePath(String.format(RESPONSIBILITY_LIST + "/" + DELETE_ALL))
                .body(hardDeleteRequest)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }
}
