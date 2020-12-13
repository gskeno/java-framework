package com.gson.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleCreditCardAppenderUse {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleCreditCardAppenderUse.class);
    //java -Dlogback.configurationFile=logback-credit-card-console.xml ConsoleCreditCardAppenderUse
    public static void main(String[] args) {
        logger.info("creditCard0123456789123");
        logger.info("creditCard01234567891234");
        logger.info("creditCard012345678912345");
        logger.info("creditCard0123456789123456");
        logger.info("creditCard01234567891234567");
        logger.info("creditCard012345678912345678");
    }
}
