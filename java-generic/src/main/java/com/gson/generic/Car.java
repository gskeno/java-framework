package com.gson.generic;

public class Car implements TrafficTool {
    @Override
    public void execute() {
        System.out.println("汽车出行");
    }
}
