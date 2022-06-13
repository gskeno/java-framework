package com.gson.pico.container.introduce;

public class Food {
    private Water water;

    private Beef beef;

    private Pig pig;

    private Vegetable vegetable;

    public Food(){

    }

    public Food(Water water, Beef beef, Pig pig) {
        this.water = water;
        this.beef = beef;
        this.pig = pig;
        System.out.println("执行了water, beef, pig 构造函数");
    }

    public Food(Water water, Beef beef, Vegetable vegetable){
        this.water = water;
        this.beef = beef;
        this.vegetable = vegetable;
        System.out.println("执行了water, beef, vegetable 构造函数");
    }

//    public Food(Water water, Beef beef, Pig pig, Vegetable vegetable) {
//        this.water = water;
//        this.beef = beef;
//        this.pig = pig;
//        this.vegetable = vegetable;
//        System.out.println("执行了water, beef, pig, vegetable构造函数");
//    }


    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        System.out.println("setWater invoked in Food");
        this.water = water;
    }

    public Beef getBeef() {
        return beef;
    }

    public void setBeef(Beef beef) {
        System.out.println("setBeef invoked in Food");

        this.beef = beef;
    }

    public Pig getPig() {
        return pig;
    }

    public void setPig(Pig pig) {
        System.out.println("setPig invoked in Food");

        this.pig = pig;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        System.out.println("vegetable invoked in Food");
        this.vegetable = vegetable;
    }

    @Override
    public String toString() {
        return "Food{" +
                "water=" + water +
                ", beef=" + beef +
                ", pig=" + pig +
                ", vegetable=" + vegetable +
                '}';
    }
}
