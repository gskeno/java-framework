package com.gson.spi.apple.juice;


import com.gson.spi.apple.Apple;
import com.gson.spi.apple.AppleDriver;

public class AppleJuiceDriver implements AppleDriver {
    @Override
    public void process(Apple apple) {
        System.out.println("将苹果做成苹果汁，更解渴");
    }
}
