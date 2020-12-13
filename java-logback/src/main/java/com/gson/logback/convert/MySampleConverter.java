package com.gson.logback.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MySampleConverter extends ClassicConverter {
    long start = System.nanoTime();

    @Override
    public String convert(ILoggingEvent event) {
        long nowInNanos = System.nanoTime();
        return Long.toString(nowInNanos-start);
    }
}
