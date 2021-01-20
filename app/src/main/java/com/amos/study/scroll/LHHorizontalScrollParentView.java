package com.amos.study.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author: amos
 * @date: 2020/12/22 13:58
 * @description:
 */
public class LHHorizontalScrollParentView extends LinearLayout {
    public LHHorizontalScrollParentView(Context context) {
        this(context, null);
    }

    public LHHorizontalScrollParentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LHHorizontalScrollParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LHHorizontalScrollParentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }
}
