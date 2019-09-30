package com.gson.java8.method_reference;

import java.util.Random;

public class Product {

    public int price;

    public Product() {
        this.price = new Random().nextInt();
        System.out.println("Product 构造器执行, price = " + price);
    }

    public void getPrice(Product product) {
        System.out.println("price =" + product.price);
    }

    public void repair(){

    }
}
