package com.common.ui.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 14:04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface OnTabSelectedListener<D> {
    void onTabSelectedChange(int index, @Nullable D prevInfo, @NonNull D nextInfo);
}
