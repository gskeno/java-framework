package com.gsonkeno.javagenerics;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierOffShelfHandler implements EventHandler<SupplierOffShelfEvent>{


    @Override
    public void handle(SupplierOffShelfEvent event) {
        log.warn("SupplierOffShelfHandler, event {}", JSON.toJSONString(event));
    }
}
