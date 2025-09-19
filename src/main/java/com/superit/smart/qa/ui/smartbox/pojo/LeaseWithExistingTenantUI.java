package com.superit.smart.qa.ui.smartbox.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class LeaseWithExistingTenantUI extends LeaseWithDebitorUI {
    @NotNull String debitor;
    @NotNull String debitorId;
}