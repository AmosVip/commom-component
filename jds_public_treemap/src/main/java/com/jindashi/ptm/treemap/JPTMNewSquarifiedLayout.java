package com.jindashi.ptm.treemap;

import java.util.List;

/**
 * 云图算法
 */
public class JPTMNewSquarifiedLayout implements JPTMMapLayout {

    @Override
    public void layout(List<JPTMIItemContract> itemList, Rect bounds) {
        layout(itemList, 0, itemList.size(), bounds);
    }

    private void layout(List<JPTMIItemContract> itemList, int start, int end, Rect bounds) {
        if (start > end) return;

        if (end - start <= 1) {
            itemList.get(start).setBoundRect(bounds);
            return;
        }

        //计算总值
        double total = sum(itemList, start, end);
        double halfTotal = total / 2; //取一半的值
        //循环判断当前的值是否大于一半的值 一分为二
        int mid = end - 1;
        double tempTotal = 0;
        for (int i = start; i < end; i++) {
            if (tempTotal > halfTotal) {
                mid = i;
                break;
            }
            tempTotal += itemList.get(i).getCalculateValue();
        }
        //分为两份总值
        double aTotal = 0;
        for (int aIndex = start; aIndex < mid; aIndex++) {
            aTotal += itemList.get(aIndex).getCalculateValue();
        }
        //
        double aRatio = 0;
        if (total > 0) {
            aRatio = aTotal / total;
        } else {
            aRatio = 0.5;
        }
        //
        Rect aRect, bRect;
        double aWidth, aHeight, bWidth, bHeight;
        double totalWidth = bounds.w;
        double totalHeight = bounds.h;
        boolean isHorizontal = totalWidth > totalHeight;

        if (isHorizontal) {
            aWidth = totalWidth * aRatio;
            bWidth = totalWidth - aWidth;
            aHeight = bHeight = totalHeight;
            aRect = new Rect(bounds.x, bounds.y, aWidth, aHeight);
            bRect = new Rect(bounds.x + aWidth, bounds.y, bWidth, bHeight);
        } else {
            if (total == 0) {
                aWidth = aHeight = bWidth = bHeight = 0;
                aRect = new Rect(bounds.x, bounds.y, aWidth, aHeight);
                bRect = new Rect(bounds.x, bounds.y, 0, 0);
            } else {
                aWidth = bWidth = totalWidth;
                aHeight = totalHeight * aRatio;
                bHeight = totalHeight - aHeight;
                aRect = new Rect(bounds.x, bounds.y, aWidth, aHeight);
                bRect = new Rect(bounds.x, bounds.y + aHeight, bWidth, bHeight);
            }
        }

        //递归
        layout(itemList, start, mid, aRect);
        layout(itemList, mid, end, bRect);
    }


    private double sum(List<JPTMIItemContract> itemList, int start, int end) {
        double s = 0;
        for (int i = start; i < end; i++)
            s += itemList.get(i).getCalculateValue();
        return s;
    }
}
