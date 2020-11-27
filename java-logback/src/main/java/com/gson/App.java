package com.gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws UnsupportedEncodingException {
        String info = "高嵩";
        byte[] bytes = info.getBytes("UTF-8");
        String s = new String(bytes);
        logger.info(s);
    }
}
