package com.gson.generic;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        Traffic<Car> carLandTraffic = new LandTraffic<>();
        carLandTraffic.travel(car);

        Bicycle bicycle = new Bicycle();
        Traffic<Bicycle> bicycleTraffic = new LandTraffic<>();
        bicycleTraffic.travel(bicycle);

        List<String> list = new ArrayList<>();
        list.add("1");
    }
}
