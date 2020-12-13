package com.gson.logback.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GEEventEvaluatorUse {
    //启动异常 GEventEvaluator已经不再使用
    private static final Logger logger = LoggerFactory.getLogger(GEEventEvaluatorUse.class);
    //java -Dlogback.configurationFile=logback-geeventevaluator.xml GEEventEvaluatorUse
    public static void main(String[] args) {
        logger.info("HelloWorld");
    }
}
