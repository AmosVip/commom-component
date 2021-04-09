package com.study.stockcloud.view;

import android.content.res.Resources;

import java.text.DecimalFormat;

/**
 * Created by hezhiqiang on 2018/5/9.
 */

public class ViewUtils {
    public ViewUtils() {
    }

    public static float dp2px(Resources resources, float dp) {
        float scale = resources.getDisplayMetrics().density;
        return dp * scale;
    }

    public static float sp2px(Resources resources, float sp) {
        float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static String formatData(double value) {
        if(value == 0.0D) {
            return "0.0";
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String result = "";
            if(Math.abs(value) < 1000000.0D) {
                result = decimalFormat.format(value);
            } else if(Math.abs(value) < 1.0E8D) {
                result = decimalFormat.format(value / 10000.0D) + "万";
            } else if(Math.abs(value) < 1.0E9D) {
                result = decimalFormat.format(value / 1.0E8D) + "亿";
            } else if(Math.abs(value) < 1.0E10D) {
                result = (new DecimalFormat("#.0")).format(value / 1.0E8D) + "亿";
            } else if(Math.abs(value) < 1.0E12D) {
                result = (new DecimalFormat("#")).format(value / 1.0E8D) + "亿";
            } else if(Math.abs(value) < -7.27379968E9D) {
                result = decimalFormat.format(value / 1.0E12D) + "万亿";
            } else if(Math.abs(value) < -7.27379968E10D) {
                result = (new DecimalFormat("#.0")).format(value / 1.0E12D) + "万亿";
            } else {
                result = (new DecimalFormat("#")).format(value / 1.0E12D) + "万亿";
            }

            return result;
        }
    }

    public static String formatDouble(double value) {
        if(value == 0.0D) {
            return "0";
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(value);
        }
    }
}
