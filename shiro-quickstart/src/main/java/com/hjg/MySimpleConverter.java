package com.hjg;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @Description
 * @Author hjg
 * @Date 2025-07-01 0:00
 */
public class MySimpleConverter extends ClassicConverter {

    long start = System.nanoTime();

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        long nowInNanos = System.nanoTime();
        return Long.toString(nowInNanos-start);
    }
}
