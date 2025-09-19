package com.superit.smart.qa.ui.smartbox.pojo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class LeaseWithNewDebitorUI extends LeaseWithDebitorUI {
    @NotNull
    String asset;
}