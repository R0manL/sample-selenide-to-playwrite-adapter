package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.CreditRatingAgency;
import com.superit.smart.qa.ui.smartbox.enums.CreditRatingType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.addIfNotNullOrEmpty;
import static com.superit.smart.qa.utils.StringUtils.getTextFrom;


@Data
@Builder
public class RatingUI {
    LocalDate start;
    CreditRatingAgency agency;
    CreditRatingType type;
    String ratingKey;
    String ratingValue;
    LocalDate ratingDate;
    String comment;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        addIfNotNullOrEmpty(result, getTextFrom(start));
        addIfNotNullOrEmpty(result, agency.toString());
        addIfNotNullOrEmpty(result, type.toString());
        addIfNotNullOrEmpty(result, ratingKey);
        addIfNotNullOrEmpty(result, ratingValue);
        addIfNotNullOrEmpty(result, getTextFrom(ratingDate));
        addIfNotNullOrEmpty(result, comment);

        return result;
    }
}
