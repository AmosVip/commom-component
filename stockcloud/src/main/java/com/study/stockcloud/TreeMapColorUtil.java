package com.study.stockcloud;

/**
 * Created by hezhiqiang on 2018/1/19.
 */

public class TreeMapColorUtil {
    public static String[] colors = {"#FF484A","#C43333","#98403F","#7F8E9D","#346D5D","#219172","#32BB81"};

    /**
     * 根据涨跌幅来获取对应的颜色
     * @param curValue 当前值
     * @param max 最大涨幅
     * @param min 最大跌幅
     * @return
     */
    public static String getTargetColor(double curValue,double max,double min) {
        String color = "";
        if(curValue > 0) {
            if(curValue <= max && curValue > (2/3.0)*max) {
                color = colors[0];
            } else if(curValue <= 2/3.0*max && curValue > 1/3.0*max) {
                color = colors[1];
            } else {
                color = colors[2];
            }
        } else if(curValue < 0) {
            if(Math.abs(curValue) <= Math.abs(min) && Math.abs(curValue) > Math.abs(2/3.0*min)) {
                color = colors[6];
            } else if(Math.abs(curValue) <= Math.abs(2/3.0*min) && Math.abs(curValue) > Math.abs(1/3.0*min)) {
                color = colors[5];
            } else {
                color = colors[4];
            }
        } else {
            color = colors[3];
        }
        return color;
    }
}
