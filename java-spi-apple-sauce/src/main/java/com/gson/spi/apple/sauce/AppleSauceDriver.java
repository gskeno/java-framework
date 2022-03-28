package com.gson.spi.apple.sauce;

import com.gson.spi.apple.Apple;
import com.gson.spi.apple.AppleDriver;

public class AppleSauceDriver implements AppleDriver {
    @Override
    public void process(Apple apple) {
        System.out.println("将苹果做成苹果泥，更丝滑");
    }
}
