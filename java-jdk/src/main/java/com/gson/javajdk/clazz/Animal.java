package com.gson.javajdk.clazz;

public class Animal {


    public Animal(){
        System.out.println("Animal构造方法");
    }

    public Animal(Animal animal){
        System.out.println("Animal is" + animal);
    }

    public Animal(String name){
        System.out.println("animal name is " + name);
    }
}
