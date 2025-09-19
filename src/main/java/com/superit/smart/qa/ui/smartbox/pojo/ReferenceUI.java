package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.ReferenceTypeVersion;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class ReferenceUI {
    @NotNull ReferenceTypeVersion type;
    @NotNull String businessPartnerReference;
    LocalDate begin;
    LocalDate end;
    String comment;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();

        if (begin != null) {
            result.add(getTextFrom(begin));
        }

        if (end != null) {
            result.add(getTextFrom(end));
        }

        result.add(type.getValue());
        result.add(businessPartnerReference);

        if (comment != null) {
            result.add(getTextFrom(comment));
        }

        return result;
    }
}