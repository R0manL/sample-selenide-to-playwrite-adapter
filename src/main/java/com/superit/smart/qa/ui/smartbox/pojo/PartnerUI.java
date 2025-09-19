package com.superit.smart.qa.ui.smartbox.pojo;

import com.superit.smart.qa.ui.smartbox.enums.Role;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@SuperBuilder
@Data
public class PartnerUI {
    private @NotNull String id;
    private @NotNull String shortDesignation;
    private Role role;
}