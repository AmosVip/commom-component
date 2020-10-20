package com.amos.study.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author: amos
 * @date: 2020/9/8 17:28
 * @description: 嵌套滚动的父类
 */
class NestedParentLayout extends LinearLayout {
    public NestedParentLayout(Context context) {
        super(context);
    }

    public NestedParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NestedParentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
