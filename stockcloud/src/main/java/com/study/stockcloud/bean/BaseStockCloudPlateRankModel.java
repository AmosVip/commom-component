package com.study.stockcloud.bean;

import java.io.Serializable;

/**
 * @author: amos
 * @date: 2021/4/8 16:21
 * @description:
 */
public abstract class BaseStockCloudPlateRankModel {
    public abstract double getRate();

    public abstract double getTuov();

    public abstract double getProportion();

    public abstract String getLabel();
}
