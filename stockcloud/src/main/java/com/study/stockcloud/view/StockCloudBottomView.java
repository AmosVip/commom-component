package com.study.stockcloud.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.stockcloud.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 介绍：这里写介绍
 * 作者：sunwentao
 * 邮箱：wentao.sun@yintech.cn
 * 时间: 18/5/18
 */
public class StockCloudBottomView extends View {
    private Paint mTitleTextPaint;
    private Paint mBottomTextPaint;
    private Paint mRectPaint;
    public static int PER_TYPE = 0;
    public static int MONERY_TYPE = 1;
    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int color5;
    private int color6;
    private int color7;
    private List<Integer> colorlist = new ArrayList<>();
    private int rectBottoY = 25;
    private int titleTextY = 10;
    private int type = 0;
    private double mMax;
    private double mMin;
    private int rectWidth;

    public StockCloudBottomView(Context context) {
        this(context, null);
    }

    public StockCloudBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StockCloudBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(getResources().getColor(R.color.ggt_login_black));
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
        color1 = getResources().getColor(R.color.cloud_one);
        color2 = getResources().getColor(R.color.cloud_two);
        color3 = getResources().getColor(R.color.cloud_three);
        color4 = getResources().getColor(R.color.cloud_four);
        color5 = getResources().getColor(R.color.cloud_five);
        color6 = getResources().getColor(R.color.cloud_six);
        color7 = getResources().getColor(R.color.cloud_seven);
        colorlist.add(color1);
        colorlist.add(color2);
        colorlist.add(color3);
        colorlist.add(color4);
        colorlist.add(color5);
        colorlist.add(color6);
        colorlist.add(color7);
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
        mTitleTextPaint.setColor(getResources().getColor(R.color.color_white));
        mTitleTextPaint.setStrokeWidth(3);
        mTitleTextPaint.setTextSize(textSize);
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mRectPaint.setColor(getResources().getColor(R.color.one));
        mBottomTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomTextPaint.setColor(getResources().getColor(R.color.color_white));
        mBottomTextPaint.setStrokeWidth(3);
        mBottomTextPaint.setTextSize(textSize);
        mBottomTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectWidth = getWidth() - getPaddingLeft();
        int bottomTextY = rectBottoY + titleTextY;
        if (type == PER_TYPE) {
            canvas.drawText("地图面积越大代表市值越大，地图红绿颜色深浅代表涨跌幅大小", dp2px(5), dp2px(titleTextY), mTitleTextPaint);
            for (int i = 0; i < colorlist.size(); i++) {
                if (i == 0) {
                    mRectPaint.setColor(colorlist.get(i));
                    Rect rect = new Rect(getPaddingLeft(), dp2px(15), (rectWidth / colorlist.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                    canvas.drawRect(rect, mRectPaint);
                    canvas.drawText(doubleToString(mMax * (1 - (1 * (double) i) / 3)) + "%", rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
                } else {
                    mRectPaint.setColor(colorlist.get(i));
                    Rect rect = new Rect((rectWidth / colorlist.size()) * i + 8 + getPaddingLeft(), dp2px(15), (rectWidth / colorlist.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                    canvas.drawRect(rect, mRectPaint);
                    canvas.drawText(doubleToString(mMax * (1 - (1 * (double) i) / 3)) + "%", rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
                }
            }
        } else {
            canvas.drawText("地图面积越大代表市值越大，地图红绿颜色深浅代表资金流入/流出", dp2px(5), dp2px(titleTextY), mTitleTextPaint);
            for (int i = 0; i < colorlist.size(); i++) {
                if (i == 0) {
                    mRectPaint.setColor(colorlist.get(i));
                    Rect rect = new Rect(getPaddingLeft(), dp2px(15), (rectWidth / colorlist.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                    canvas.drawRect(rect, mRectPaint);
                    canvas.drawText(getNumString(getNum(mMax) * (1 - (1 * (double) i) / 3)), rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
                } else if (i > 0 && i <= 3) {
                    mRectPaint.setColor(colorlist.get(i));
                    Rect rect = new Rect((rectWidth / colorlist.size()) * i + 8 + getPaddingLeft(), dp2px(15), (rectWidth / colorlist.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                    canvas.drawRect(rect, mRectPaint);
                    canvas.drawText(getNumString(getNum(mMax) * (1 - (1 * (double) i) / 3)), rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
                } else {
                    mRectPaint.setColor(colorlist.get(i));
                    Rect rect = new Rect((rectWidth / colorlist.size()) * i + 8 + getPaddingLeft(), dp2px(15), (rectWidth / colorlist.size()) * (i + 1) + getPaddingLeft(), dp2px(rectBottoY));
                    canvas.drawRect(rect, mRectPaint);
                    canvas.drawText(getNumString(getNum(mMin) * (1 - (1 * (double) i) / 3)), rect.centerX(), dp2px(bottomTextY), mBottomTextPaint);
                }
            }
        }


    }


    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }


    public void setData(double max, double min, int type) {
        this.type = type;
        this.mMax = max;
        this.mMin = min;
        invalidate();
    }


    private String getNumString(double num) {
        if (Math.abs(num) < 100000000 && Math.abs(num) > 1000000) {
            double n = num / 10000000;
            //千万
            return (doubleToString(n) + "千万");
        } else if (Math.abs(num) >= 100000000) {
            double m = (num / 100000000);
            return (doubleToString(m) + "亿");
        }

        return doubleToString(num / 1000000) + "";
    }


    private double getNum(double num) {
        double d = num;
        BigDecimal bd = new BigDecimal(d);
        return bd.doubleValue();
    }

}
