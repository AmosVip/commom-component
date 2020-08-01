package com.common.ui.tab.bottom;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 10:36
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CTabBottomInfo<Color> {
    public enum TabType {
        BITMAP,
        ICON
    }

    public String name;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;

    public String iconFontPath;
    /**
     * Tips：在Java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */
    public String defaultIconFont;
    public String selectedIconFont;

    public Color defaultColor;
    public Color tintColor;

    public TabType tabType;

    public CTabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap, Color defaultColor, Color tintColor) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.BITMAP;
    }

    public CTabBottomInfo(String name, String iconFontPath, String defaultIconFont, String selectedIconFont, Color defaultColor, Color tintColor) {
        this.name = name;
        this.iconFontPath = iconFontPath;
        this.defaultIconFont = defaultIconFont;
        this.selectedIconFont = selectedIconFont;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }


}
