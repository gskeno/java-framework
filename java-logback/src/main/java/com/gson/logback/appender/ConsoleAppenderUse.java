package com.gson.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleAppenderUse {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleAppenderUse.class);
    //java -Dlogback.configurationFile=logback-console.xml ConsoleAppenderUse
    public static void main(String[] args) {
        logger.info("ConsoleAppenderUse");
    }
}
