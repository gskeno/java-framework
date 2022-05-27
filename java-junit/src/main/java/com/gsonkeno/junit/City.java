package com.gsonkeno.junit;

public class City implements Livable{
    @Override
    public void live() {
        System.out.println("城市是适宜居住的");
    }
}
