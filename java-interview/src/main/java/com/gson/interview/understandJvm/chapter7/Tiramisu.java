package com.gson.interview.understandJvm.chapter7;

@Factory(type=Tiramisu.class, id="Tiramisu")
public class Tiramisu implements Meal{

    @Override
    public float getPrice() {
        return 4.5f;
    }
}
