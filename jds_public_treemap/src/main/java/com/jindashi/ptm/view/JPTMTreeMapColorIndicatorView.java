package com.jindashi.ptm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jindashi.ptm.R;
import com.jindashi.ptm.utils.TreeMapColorUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JPTMTreeMapColorIndicatorView extends View {
    private Paint mTitleTextPaint;
    private Paint mBottomTextPaint;
    private Paint mRectPaint;
    private List<Integer> colorList = new ArrayList<>();
    private int rectBottoY = 25;
    private int titleTextY = 10;
    private double mMax;
    private double mMin;
    private int rectWidth;
    private String mTipWord = ""; //提示文案

    public JPTMTreeMapColorIndicatorView(Context context) {
        this(context, null);
    }

    public JPTMTreeMapColorIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JPTMTreeMapColorIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(getResources().getColor(R.color.jptm_color_262933));
        initAttrs(context, attrs);
        initView();
        initColor();

    }

    private float textSize;

    private void initAttrs(Context context, AttributeSet attrs) {
       /* TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.stockCloudBottomView);
        textSize = ta.getDimension(R.styleable.stockCloudBottomView_bottomTextSize,sp2px(10));
        ta.recycle();*/
        textSize = sp2px(10);
    }

    private void initColor() {
        //color1 = getResources().getColor(R.color.jptm_cloud_one);
        for (String color : TreeMapColorUtil.colors) {
            colorList.add(Color.parseColor(color));
        }
    }


    private int dp2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    private void initView() {
        mTitleTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitleTextPaint.setColor(getResources().getColor(R.color.jptm_color_white));
        mTitleTextPaint.setStrokeWidth(3);
        mTitleTextPaint.setTextSize(textSize);
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBottomTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomTextPaint.setColor(getResources().getColor(R.color.jptm_color_white));
        mBottomTextPaint.setStrokeWidth(3);
        mBottomTextPaint.setTextSize(textSize);
        mBottomTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectWidth = getMeasuredWidth() - getPaddingLeft();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bottomTextY = rectBottoY + titleTextY;
        if (!TextUtils.isEmpty(mTipWord)) {
            canvas.drawText(mTipWord, dp2px(5), dp2px(titleTextY), mTitleTextPaint);
        }
        for (int i = 0; i < colorList.size(); i++) {
            if (i == 0) {
                mRectPaint.setColor(colorList.get(i));
                Rect rect = new Rect(getPaddingLeft(),
                        dp2px(15),
                        (rectWidth / colorList.size()) * (i + 1) + getPaddingLeft(),
                        dp2px(rectBottoY));
                canvas.drawRect(rect, mRectPaint);
                canvas.drawText(doubleToString(mMax * (1 - (1 * (double) i) / 3)) + "%", rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
            } else {
                mRectPaint.setColor(colorList.get(i));
                Rect rect = new Rect((rectWidth / colorList.size()) * i + 8 + getPaddingLeft(), dp2px(15), (rectWidth / colorList.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                canvas.drawRect(rect, mRectPaint);
                canvas.drawText(doubleToString(mMax * (1 - (1 * (double) i) / 3)) + "%", rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
            }
        }

    }


    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    private String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }


    public void setData(double max, double min, String tipsWord) {
        this.mTipWord = tipsWord;
        updateMaxAndMinValue(max, min);
    }

    public void updateMaxAndMinValue(double max, double min) {
        this.mMax = max;
        this.mMin = min;
        invalidate();
    }

}
