package com.study.stockcloud.view;

import android.graphics.RectF;
import androidx.annotation.NonNull;

import com.study.stockcloud.treemap.MapItem;
import com.study.stockcloud.treemap.Rect;


/**
 * Created by hezhiqiang on 2018/1/19.
 */

public class TreeMapItem<T> extends MapItem implements TreeMappable, Comparable<TreeMapItem> {
    private double weight;
    private String label;
    private String reference;
    private String color;
    private T data;

    public TreeMapItem(double weight,String reference,String color, String label,T t) {
        this.label = label;
        this.weight = weight;
        this.data = t;
        this.reference = reference;
        this.color = color;
        setSize(weight);
    }

    @Override
    public RectF getBoundsRectF() {
        Rect bounds = getBounds();
        return new RectF(Double.valueOf(bounds.x).floatValue(),
                Double.valueOf(bounds.y).floatValue(),
                Double.valueOf(bounds.x).floatValue() + Double.valueOf(bounds.w).floatValue(),
                Double.valueOf(bounds.y).floatValue() + Double.valueOf(bounds.h).floatValue());
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public T getData() {
        return data;
    }

    public String getReference() {
        return reference;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int compareTo(@NonNull TreeMapItem o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return TreeMapItem.class.getSimpleName() + "[label=" + label + ",weight=" + weight +
                ",bounds=" + getBounds().toString() +
                ",boundsRectF=" + getBoundsRectF().toString() + "]";
    }
}
