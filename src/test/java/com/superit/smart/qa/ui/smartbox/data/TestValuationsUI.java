package com.superit.smart.qa.ui.smartbox.data;

import com.superit.smart.qa.ui.smartbox.pojo.ValuationUI;

import java.time.LocalDate;

import static com.superit.smart.qa.ui.smartbox.data.PredAppraisalType.TYPE_001_FUND_SURVEY;
import static com.superit.smart.qa.ui.smartbox.data.PredAppraisalType.TYPE_002_PRIVAT_SURVEY;
import static com.superit.smart.qa.utils.DateUtils.generateRandomDate;
import static com.superit.smart.qa.utils.NumberUtils.generateRandomThreeDigitsNumber;

public class TestValuationsUI {

    private TestValuationsUI() {
    }

    public static final ValuationUI TEST_VALUATION_TC53901 =
            ValuationUI.builder()
                    .asset(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_4700_4701.getName())
                    .assetId(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_4700_4701.getValue())
                    .dateOfAppraisal(LocalDate.now().plusDays(generateRandomThreeDigitsNumber()))
                    .realEstateAppraisalType(TYPE_001_FUND_SURVEY)
                    .marketValue(generateRandomThreeDigitsNumber())
                    .build();

    public static final ValuationUI TEST_VALUATION_TC102927 =
            ValuationUI.builder()
                    .asset(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_4700_4701.getName())
                    .assetId(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_4700_4701.getValue())
                    .dateOfAppraisal(generateRandomDate())
                    .realEstateAppraisalType(TYPE_002_PRIVAT_SURVEY)
                    .marketValue(generateRandomThreeDigitsNumber())
                    .build();

    public static final ValuationUI VALUATION_TC102920 =
            ValuationUI.builder()
                    .asset(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_ID_4700_4709_00180_LONNROTINKATU_34.getName())
                    .assetId(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_ASSETS.ASSET_ID_4700_4709_00180_LONNROTINKATU_34.getValue())
                    .dateOfAppraisal(generateRandomDate())
                    .realEstateAppraisalType("type_002 Privat survey")
                    .marketValue(generateRandomThreeDigitsNumber())
                    .build();

    public static final ValuationUI VALUATION_TC102926 =
            ValuationUI.builder()
                    .asset(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_MARKET_VALUE_APPRAISALS.MARKET_VALUE_APPRAISAL_4700_4700_MADRID_AV_CONCHA_ESPINA.getName())
                    .assetId(PredTestData.GLOBAL_GROUP_ON_MASTER_DATA_PAGE.ITEM_REAL_ESTATE_MARKET_VALUE_APPRAISALS.MARKET_VALUE_APPRAISAL_4700_4700_MADRID_AV_CONCHA_ESPINA.getId())
                    .dateOfAppraisal(generateRandomDate())
                    .realEstateAppraisalType("type_002 Privat survey")
                    .marketValue(generateRandomThreeDigitsNumber())
                    .build();
}