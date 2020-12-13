package com.gson.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleEvaluatorUse {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleEvaluatorUse.class);
    //java -Dlogback.configurationFile=logback-evaluator-console.xml ConsoleEveluatorUse
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                logger.debug("who calls thee?");
            } else {
                logger.debug("I know me " + i);
            }
        }
    }
}
