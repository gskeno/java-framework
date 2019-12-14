package com.gson.lang;

public class Cat extends Animal {
    //子类的name就是子类的，父类的name就是父类的
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("cat");

        System.out.println(cat.getName());
        System.out.println(cat.getAnimalName());
    }
}
