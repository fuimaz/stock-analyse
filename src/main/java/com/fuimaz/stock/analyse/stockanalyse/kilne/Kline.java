package com.fuimaz.stock.analyse.stockanalyse.kilne;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Kline {
    @JsonFormat(pattern = "yy-MM-dd hh:mm", timezone = "GMT+8")
    private Date date;
    private double close;
    private double open;
    private double high;
    private double low = Double.MAX_VALUE;
    private int vol;

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        return dateFormat.format(date) + ", " + close + ", " + open + ", " + high + ", " + low + ", " + vol;
    }
}
