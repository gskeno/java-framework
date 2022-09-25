package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public class Client {
    public static void main(String[] args) {
//        ArrayList<String> sequeue = new ArrayList<>();
//
//        sequeue.add("start");
//        sequeue.add("alarm");
//        sequeue.add("engineBoom");
//        sequeue.add("stop");
//
//        BMWBuilder bmwBuilder = new BMWBuilder();
//        bmwBuilder.setSequeue(sequeue);
//        CarModel carModel = bmwBuilder.getCarModel();
//        carModel.run();

        Director director = new Director();

        for (int i = 0; i < 100; i++) {
            director.getABenzModel().run();
            System.out.println();
        }
    }
}
