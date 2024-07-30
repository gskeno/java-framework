package com.gsonkeno.javagenerics;

import org.junit.Test;

public class ConsumerTest {
    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        MessageConsumer consumer = new MessageConsumer();
        consumer.consume("{\"eventType\":\"SUPPLIER_OFF_SHELF\",\"messageBody\":\"{\\\"supplierId\\\":\\\"123\\\",\\\"productId\\\":\\\"456\\\"}\"}");

    }
}
