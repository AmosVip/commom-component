package com.jindashi.ptm.treemap;

import android.graphics.RectF;

/**
 * @author: amos
 * @date: 2021/4/14 18:23
 * @description: 此类用来承载绘制 所需要的数据
 */
public class JPTMTreeMapItem implements JPTMIItemContract {
    private Rect boundRect; //绘制面积
    private double calculateValue; //用于计算面积的值
    private String title; //标题
    private String fundsWord; //资金
    private String upDownFloatWord; //涨跌幅
    private int bgColor; //绘制的颜色
    private int sourceDataPosition; //源数据的索引

    private JPTMTreeMapItem() {

    }

    public JPTMTreeMapItem(double calculateValue, String title, String fundsWord, String upDownFloatWord, int sourceDataPosition) {
        this.calculateValue = calculateValue;
        this.title = title;
        this.fundsWord = fundsWord;
        this.upDownFloatWord = upDownFloatWord;
        this.sourceDataPosition = sourceDataPosition;
    }

    public RectF getBoundsRectF() {
        if (boundRect == null) {
            boundRect = new Rect();
        }
        Rect bounds = boundRect;
        return new RectF(Double.valueOf(bounds.x).floatValue(),
                Double.valueOf(bounds.y).floatValue(),
                Double.valueOf(bounds.x).floatValue() + Double.valueOf(bounds.w).floatValue(),
                Double.valueOf(bounds.y).floatValue() + Double.valueOf(bounds.h).floatValue());
    }

    public Rect getBoundRect() {
        return boundRect;
    }

    public void setBoundRect(Rect boundRect) {
        this.boundRect = boundRect;
    }

    public double getCalculateValue() {
        return calculateValue;
    }

    public void setCalculateValue(double calculateValue) {
        this.calculateValue = calculateValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getFundsWord() {
        return fundsWord;
    }

    public void setFundsWord(String fundsWord) {
        this.fundsWord = fundsWord;
    }

    public String getUpDownFloatWord() {
        return upDownFloatWord;
    }

    public void setUpDownFloatWord(String upDownFloatWord) {
        this.upDownFloatWord = upDownFloatWord;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getSourceDataPosition() {
        return sourceDataPosition;
    }

    public void setSourceDataPosition(int sourceDataPosition) {
        this.sourceDataPosition = sourceDataPosition;
    }
}
