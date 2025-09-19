package com.superit.smart.qa.api.smartbox.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BankAccountValue {
    private String accountdescription;
    private Object accountcurrency;
    private String accountholder;
    private String accountidonlyaccount;
    private String accountnumber;
    private Object accountvalidfrom;
    private Object accountvalidto;
    private Integer bank;
    private Boolean extinct;
    private Object finaccpurpose;
    private Object iban;
    private Boolean lockflag;
    private Integer lparentid;
    private Object typeofbankaccount;
    private Integer lid;
    private BankLink bankLink;
    private BankAccountPropertiesLink propertiesLink;
    private Object typeofbankaccountKeyValue;
    private Object accountcurrencyKeyValue;

    @Data
    public class BankLink {
        private String designationshort;
        private String id;
        private Integer lid;
    }

    @Data
    public class BankAccountPropertiesLink {
        private String designationshort;
        private String id;
        private Integer lid;
    }
}
