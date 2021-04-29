package com.fuimaz.stock.analyse.stockanalyse.controller;

import com.fuimaz.stock.analyse.stockanalyse.kilne.Kline;
import com.fuimaz.stock.analyse.stockanalyse.source.CsvLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/kline")
public class KlineController {

    @RequestMapping("/get")
    public List<Kline> get() {
        return CsvLoader.csvParser("SH600961");
    }
}
