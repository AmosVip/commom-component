package com.study.stockcloud.view;

import android.graphics.RectF;

import com.study.stockcloud.treemap.Mappable;


/**
 * Created by hezhiqiang on 2018/1/19.
 */

public interface TreeMappable<T> extends Mappable {

    RectF getBoundsRectF();
    String getLabel();
    T getData();
}
