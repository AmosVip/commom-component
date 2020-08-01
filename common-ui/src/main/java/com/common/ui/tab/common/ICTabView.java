package com.common.ui.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 10:16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface ICTabView<D> {

    void setTabInfo(@NonNull D info);

    void resetHeight(@Px int height);
}
