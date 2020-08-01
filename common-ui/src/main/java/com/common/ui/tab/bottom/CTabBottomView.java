package com.common.ui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.common.ui.R;
import com.common.ui.tab.common.ICTabView;

import java.util.ArrayDeque;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 10:17
 *     desc   : 单个tab item
 *     version: 1.0
 * </pre>
 */
public class CTabBottomView extends RelativeLayout implements ICTabView<CTabBottomInfo<?>> {
    public CTabBottomInfo<?> tabBottomInfo;
    private ImageView iv_icon;
    private TextView tv_icon_font;
    private TextView tv_name;

    public CTabBottomView(Context context) {
        this(context, null);
    }

    public CTabBottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CTabBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CTabBottomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.c_tab_bottom_view, this);
        iv_icon = findViewById(R.id.iv_icon);
        tv_icon_font = findViewById(R.id.tv_icon_font);
        tv_name = findViewById(R.id.tv_name);
    }

    @Override
    public void setTabInfo(@NonNull CTabBottomInfo<?> info) {
        this.tabBottomInfo = info;
        inflateInfo(false,true);
    }

    @Override
    public void resetHeight(int height) {

    }

    private void inflateInfo(boolean isSelected, boolean isInit) {
        if (tabBottomInfo.tabType == CTabBottomInfo.TabType.ICON) {
            if (isInit) {
                iv_icon.setVisibility(GONE);
                tv_icon_font.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabBottomInfo.iconFontPath);
                tv_icon_font.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    tv_name.setText(tabBottomInfo.name);
                }
            }
            if (isSelected) {
                tv_icon_font.setText(TextUtils.isEmpty(tabBottomInfo.selectedIconFont) ? tabBottomInfo.defaultIconFont :
                        tabBottomInfo.selectedIconFont);
                tv_icon_font.setTextColor(getColor(tabBottomInfo.tintColor));
                tv_name.setTextColor(getColor(tabBottomInfo.tintColor));
            } else {
                tv_icon_font.setText(tabBottomInfo.defaultIconFont);
                tv_icon_font.setTextColor(getColor(tabBottomInfo.defaultColor));
                tv_name.setTextColor(getColor(tabBottomInfo.defaultColor));
            }
            return;
        }

        if (tabBottomInfo.tabType == CTabBottomInfo.TabType.BITMAP) {
            if (isInit) {
                iv_icon.setVisibility(VISIBLE);
                tv_icon_font.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    tv_name.setText(tabBottomInfo.name);
                }
            }

            if (isSelected) {
                iv_icon.setImageBitmap(tabBottomInfo.selectedBitmap);
                tv_name.setTextColor(getColor(tabBottomInfo.tintColor));
            } else {
                iv_icon.setImageBitmap(tabBottomInfo.defaultBitmap);
                tv_name.setTextColor(getColor(tabBottomInfo.defaultColor));
            }
            return;
        }

    }

    public int getColor(@NonNull Object object) {
        if (object instanceof String) {
            return Color.parseColor((String) object);
        } else {
            return (int) object;
        }
    }
}
