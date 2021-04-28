package com.fuimaz.stock.analyse.stockanalyse.source;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SourceLoader {

    public static List<String> loadFromFile(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                lines.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            log.error("", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return lines;
    }

    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }


//    public Map<String, List<String>> loadFiles(List<String> stockList) {
//        String dir = "/Users/jiman/Downloads/1分钟线分类/8/";
//        for ()
//    }

    public static void main(String arg[]) {
        System.out.println(loadFromFile("/Users/jiman/Downloads/1分钟线分类/8/SH600860.csv"));
        System.out.println(getAllFile("/Users/jiman/Downloads/1分钟线分类/", true));
    }
}
