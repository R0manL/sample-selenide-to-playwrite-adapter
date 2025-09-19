package com.superit.smart.qa.api.smartbox.pojo.sharing_info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer lid;
    private String email;
    private String name;
}
