package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.superit.smart.qa.utils.StringUtils.getTextFrom;

@Data
@Builder
public class ResponsibilityUI {
    LocalDate validFrom;
    @NotNull
    LocalDate validUntil;
    @NotNull
    String responsibility;
    @NotNull
    String contactPerson;
    @NotNull
    String contactPersonId;
    String deputy;
    String deputyId;

    public List<String> getValues() {
        List<String> result = new ArrayList<>();
        result.add(getTextFrom(validFrom));
        result.add(getTextFrom(validUntil));
        result.add(responsibility);
        result.add(contactPerson);
        result.add(deputy);

        return result;
    }

    @Override
    public String toString() {
        return this.responsibility;
    }
}
