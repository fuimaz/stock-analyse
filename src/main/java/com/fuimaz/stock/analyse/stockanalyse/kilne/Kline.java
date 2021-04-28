package com.fuimaz.stock.analyse.stockanalyse.kilne;

import lombok.Data;

import java.util.Date;

@Data
public class Kline {
    private Date date;
    private double close;
    private double open;
    private double high;
    private double low = Double.MAX_VALUE;
    private int vol;

    @Override
    public String toString() {
        return date + ", " + close + ", " + open + ", " + high + ", " + low + ", " + vol;
    }
}
