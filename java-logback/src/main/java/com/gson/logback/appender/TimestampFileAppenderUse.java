package com.gson.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampFileAppenderUse {
    private static final Logger logger = LoggerFactory.getLogger(TimestampFileAppenderUse.class);

    //java -Dlogback.configurationFile=logback-timestamp.xml TimestampFileAppenderUse
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getNow());
        Thread.sleep(3000);
        System.out.println(getNow());
        logger.info("after sleep 3 second, time is " + getNow());
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            logger.info("TimestampFileAppenderUse" + ":" + i);
        }
    }

    public static final String getNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
