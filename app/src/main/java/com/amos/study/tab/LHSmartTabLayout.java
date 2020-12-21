package com.amos.study.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.google.android.material.tabs.TabLayout;

/**
 * @author: amos
 * @date: 2020/12/21 17:22
 * @description:
 */
public class LHSmartTabLayout extends HorizontalScrollView {
    public LHSmartTabLayout(Context context) {
        this(context,null);
    }

    public LHSmartTabLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LHSmartTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context,attrs,defStyleAttr,0);
    }

    public LHSmartTabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //TabLayout

    }
}
