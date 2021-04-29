package com.fuimaz.stock.analyse.stockanalyse.source;

import com.fuimaz.stock.analyse.stockanalyse.kilne.Kline;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CsvLoader {

    public static List<Kline> csvParser(String stockName) {
        String filePath = "/Users/jiman/Downloads/30分钟线分类/" + stockName + ".csv";
        List<String> lines = SourceLoader.loadFromFile(filePath);
        List<Kline> klines = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            Kline kline = new Kline();
            kline.setDate(new Date(split[0]));
            kline.setOpen(Double.parseDouble(split[1]));
            kline.setHigh(Double.parseDouble(split[2]));
            kline.setLow(Double.parseDouble(split[3]));
            kline.setClose(Double.parseDouble(split[4]));
            kline.setVol(Double.valueOf((split[5])).intValue());
            klines.add(kline);
        }
        return klines;
    }
}
