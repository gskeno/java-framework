package com.gson.logback.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatcherFilterUse {
    private static final Logger logger = LoggerFactory.getLogger(MatcherFilterUse.class);
    //java -Dlogback.configurationFile=evaluatorWithMatcher.xml MatcherFilterUse
    public static void main(String[] args) {
        logger.info("statement 1");
        logger.info("statement 2");
        logger.info("statement 3");
        logger.info("statement 4");
    }

}
