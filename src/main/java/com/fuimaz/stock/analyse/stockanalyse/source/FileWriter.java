package com.fuimaz.stock.analyse.stockanalyse.source;

import com.fuimaz.stock.analyse.stockanalyse.kilne.Kline;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Slf4j
public class FileWriter {
    public static void klineFileWriter(String dir, String fileName, List<Kline> klineList) {
        try {
            File f = new File(dir + fileName + ".csv");
            if (!f.exists()) {
                f.createNewFile();
            }

            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
            BufferedWriter writer = new BufferedWriter(write);
            writer.write("");
            for (Kline kline : klineList) {
                writer.append(kline.toString() + "\n");
            }

            writer.flush();
            write.close();
            writer.close();
        } catch (Exception e){
            log.error("", e);
        }
    }
}
