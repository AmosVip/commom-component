package com.common.ui.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.common.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 19:10
 *     desc   : 流式布局
 *     version: 1.0
 * </pre>
 */
public class CFlowLayout extends ViewGroup {
    private boolean isMeasure = false; //为了第一次不进行测量
    private List<List<View>> mRowViewList = new ArrayList<>(); //记录每一行的view
    private List<Integer> mRowHeightList = new ArrayList<>(); //记录每一行的行高
    private int rowGap = 20, columnGap = 20, maxRow = -1;

    public CFlowLayout(Context context) {
        this(context, null);
    }

    public CFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CFlowLayout);
            if (attributes.hasValue(R.styleable.CFlowLayout_c_flow_row_gap)) {
                rowGap = attributes.getDimensionPixelOffset(R.styleable.CFlowLayout_c_flow_row_gap, rowGap);
            }
            if (attributes.hasValue(R.styleable.CFlowLayout_c_flow_column_gap)) {
                columnGap = attributes.getDimensionPixelOffset(R.styleable.CFlowLayout_c_flow_column_gap, columnGap);
            }
            if (attributes.hasValue(R.styleable.CFlowLayout_c_flow_max_row_count)) {
                maxRow = attributes.getDimensionPixelOffset(R.styleable.CFlowLayout_c_flow_max_row_count, maxRow);
            }
            attributes.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //父容器能够给到的最大宽
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //父容器的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //父容器能够给到的最大高
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //父容器的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //每一行的最大宽度
        int rowMaxWidth = 0;
        //每一行的最大高度
        int rowMaxHeight = 0;
        //总的最大高度
        int totalRowHeight = 0;
        //总的最大宽度
        int totalRowWidth = 0;
        //每个子view 的 宽和高
        int childWidth = 0;
        int childHeight = 0;

        if (!isMeasure) {
            isMeasure = true;
        } else {
            mRowViewList.clear();
            mRowHeightList.clear();
            List<View> rowChildView = new ArrayList<>();
            for (int i = 0, count = getChildCount(); i < count; i++) {
                View childView = getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
                measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
                childWidth = params.leftMargin + params.rightMargin + childView.getMeasuredWidth();
                childHeight = params.topMargin + params.bottomMargin + childView.getMeasuredHeight();
                if (rowMaxWidth + childWidth > widthSize - getPaddingLeft() - getPaddingRight()) { //超过一行
                    totalRowWidth = Math.max(rowMaxWidth, totalRowWidth);
                    totalRowHeight += rowMaxHeight;
                    mRowViewList.add(rowChildView);
                    mRowHeightList.add(rowMaxHeight);
                    rowChildView = new ArrayList<>();
                    rowMaxWidth = childWidth;
                    rowMaxHeight = childHeight;
                } else { //未超过一行
                    rowMaxWidth += childWidth;
                    rowMaxHeight = Math.max(rowMaxHeight, childHeight);
                }
                rowChildView.add(childView);

                if (i == count - 1) { //最后一个
                    mRowViewList.add(rowChildView);
                    mRowHeightList.add(rowMaxHeight);
                    totalRowWidth = Math.max(totalRowWidth, rowMaxWidth);
                    totalRowHeight += rowMaxHeight;
                }
            }
        }

        int resultWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : totalRowWidth;
        int resultHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : totalRowHeight;
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int contentLeft = getPaddingLeft();
        int contentTop = getPaddingTop();
        for (int i = 0, count = mRowViewList.size(); i < count; i++) {
            List<View> childView = mRowViewList.get(i);
            for (View view : childView) {
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                left = contentLeft + params.leftMargin;
                top = contentTop + params.topMargin;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                contentLeft = right + params.rightMargin;
            }
            contentLeft = getPaddingLeft();
            contentTop += mRowHeightList.get(i);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
