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
        Integer a = null;
        Integer b = null;
        try{
            a = null;
            b = 10;
            System.out.println(a/b);

        }catch (Exception e){
            logger1.error("divide exception a= {}, b= {} ", a, b, e);
        }
    }
}
