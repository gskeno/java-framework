package com.gson.logback.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCUse {
    /**
     * 见配置文件
     */
    private static final Logger logger = LoggerFactory.getLogger("CONSOLE_LOGGER");

    /**
     * 执行，看看控制台会发生什么
     * @param args
     */
    public static void main(String[] args) {
        MDC.put("first", "gson");
        MDC.put("last", "keno");

        logger.info("see what is before me");

        MDC.put("first","alibaba");
        MDC.put("last","baidu");

        logger.info("see what is before me again");

    }
}
