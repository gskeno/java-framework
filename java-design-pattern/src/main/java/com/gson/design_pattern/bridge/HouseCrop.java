package com.gsonkeno.pattern.bridge;

/**
 * Created by gaosong on 2018-01-18
 */
public class HouseCrop extends Crop {

    public HouseCrop(Product product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚钱了\n");
    }
}
