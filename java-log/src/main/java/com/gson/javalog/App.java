package com.gson.javalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final Logger logger1 = LoggerFactory.getLogger("javalog");

    public static void main( String[] args )
    {
        // logger.warn("Hello World");
        logger1.info("Hello java log");
    }
}
