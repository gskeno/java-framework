package com.gson.spi.apple.sauce;


import com.gson.spi.apple.Apple;

public class Main {
    public static void main(String[] args) {
        AppleSauceDriver appleSauceDriver = new AppleSauceDriver();
        appleSauceDriver.process(new Apple());
    }
}
