package com.superit.smart.qa.ui.smartbox.pojo;
/* Created by R0manL. */

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
public final class ColumnHeaderUI {
    @NotNull
    private final String colId;
    @NotNull
    private final String columnHeaderTitleText;
    @NotNull
    private final String tippyCustomTitleText;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnHeaderUI that = (ColumnHeaderUI) o;

        if (!colId.equals(that.colId)) return false;
        if (!columnHeaderTitleText.equals(that.columnHeaderTitleText)) return false;
        return tippyCustomTitleText.equals(that.tippyCustomTitleText);
    }

    @Override
    public int hashCode() {
        int result = colId.hashCode();
        result = 31 * result + columnHeaderTitleText.hashCode();
        result = 31 * result + tippyCustomTitleText.hashCode();
        return result;
    }
}
