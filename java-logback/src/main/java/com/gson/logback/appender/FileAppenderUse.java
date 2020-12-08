package com.gson.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileAppenderUse {
    private static final Logger logger = LoggerFactory.getLogger(FileAppenderUse.class);
    //java -Dlogback.configurationFile=logback-fileAppender.xml FileAppenderUse
    public static void main(String[] args) {
        logger.info("FileAppenderUse");
    }
}
