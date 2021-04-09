package com.study.stockcloud.bean;

import java.io.Serializable;

/**
 * @author: amos
 * @date: 2021/4/8 17:17
 * @description:
 */
public class StockCloudPlateRankModel extends BaseStockCloudPlateRankModel implements Serializable {
    private double zdf;
    private String stockName;


    public StockCloudPlateRankModel(double zdf, String stockName) {
        this.zdf = zdf;
        this.stockName = stockName;
    }

    @Override
    public double getRate() {
        return zdf;
    }

    @Override
    public double getTuov() {
        return 0;
    }

    @Override
    public double getProportion() {
        return zdf;
    }

    @Override
    public String getLabel() {
        return stockName;
    }
}
