package com.gsonkeno.pattern.bridge;

/**
 * Created by gaosong on 2018-01-18
 */
public abstract class Crop {
    private Product product;

    public Crop(Product product) {
        this.product = product;
    }

    public void makeMoney(){
        product.beProduced();
        product.beSelled();
    }
}
