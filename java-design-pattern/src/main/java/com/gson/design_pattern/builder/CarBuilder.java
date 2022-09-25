package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public abstract class CarBuilder {
    public abstract void setSequeue(ArrayList<String> sequeue);

    public abstract CarModel getCarModel();
}
