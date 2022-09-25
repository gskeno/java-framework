package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmwModel = new BMWModel();

    @Override
    public void setSequeue(ArrayList<String> sequeue) {
        this.bmwModel.setSequeue(sequeue);
    }

    @Override
    public CarModel getCarModel() {
        return bmwModel;
    }
}
