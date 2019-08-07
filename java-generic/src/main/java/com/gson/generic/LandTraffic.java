package com.gson.generic;

public class LandTraffic<E extends TrafficTool> implements Traffic<E> {
    @Override
    public void travel(E e) {
        e.execute();
    }
}
