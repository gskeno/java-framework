package com.gson.javalog.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request {
    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    public static void main(String[] args) {
        logger.warn(Request.class.getName());
    }
}
