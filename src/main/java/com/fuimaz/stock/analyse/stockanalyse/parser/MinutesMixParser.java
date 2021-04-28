package com.fuimaz.stock.analyse.stockanalyse.parser;

import com.fuimaz.stock.analyse.stockanalyse.kilne.Kline;
import com.fuimaz.stock.analyse.stockanalyse.source.FileWriter;
import com.fuimaz.stock.analyse.stockanalyse.source.SourceLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class MinutesMixParser {

    public static void csvParser(String inDir, String outDir) {
        List<String> allFile = SourceLoader.getAllFile(inDir, true);
        for (String file : allFile) {
            if (!file.endsWith(".csv")) continue;
            List<String> lines = SourceLoader.loadFromFile(file);
            List<Kline> klines = new ArrayList<>();
            for (String line : lines) {
                String[] split = line.split(",");
                Kline kline = new Kline();
                kline.setDate(new Date(split[0] + " " + split[1]));
                kline.setOpen(Double.parseDouble(split[2]));
                kline.setHigh(Double.parseDouble(split[3]));
                kline.setLow(Double.parseDouble(split[4]));
                kline.setClose(Double.parseDouble(split[5]));
                kline.setVol(Double.valueOf((split[6])).intValue() * 100);
                klines.add(kline);
            }
            mixIn(klines);
            String fileName = file.substring(file.lastIndexOf("/") + 1);
            FileWriter.klineFileWriter(outDir, fileName.replace(".csv", ""), klines);
            break;
        }
    }

    public static void mixIn(List<Kline> klines) {
        List<Kline> newKlines = new ArrayList<>();
        Date splitDate = new Date(klines.get(0).getDate().getTime() - 60 * 1000);
        Kline newKline = new Kline();
        newKline.setOpen(klines.get(0).getOpen());
        for (Kline kline : klines) {
            if (kline.getDate().after(splitDate)) {
                splitDate = new Date(kline.getDate().getTime() + 29 * 60 * 1000);
//                newKline = new Kline();
            }

            if (kline.getHigh() > newKline.getHigh()) {
                newKline.setHigh(kline.getHigh());
            }

            if (kline.getLow() < newKline.getLow()) {
                newKline.setLow(kline.getLow());
            }

            newKline.setVol(newKline.getVol() + kline.getVol());

            if (kline.getDate().equals(splitDate)) {
                newKline.setClose(kline.getClose());
                newKlines.add(newKline);
                newKline = new Kline();
                newKline.setDate(kline.getDate());
                newKline.setOpen(kline.getOpen());
            }
        }

        System.out.println(newKlines);
    }

    public static void main(String arg[]) {
        csvParser("/Users/jiman/Downloads/1分钟线分类/8/", "/Users/jiman/Downloads/30分钟线分类/");
    }
}
