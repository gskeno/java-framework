package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benzModel = new BenzModel();

    @Override
    public void setSequeue(ArrayList<String> sequeue) {
        this.benzModel.setSequeue(sequeue);
    }

    @Override
    public CarModel getCarModel() {
        return this.benzModel;
    }
}
