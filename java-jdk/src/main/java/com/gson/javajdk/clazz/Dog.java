package com.gson.javajdk.clazz;

public class Dog extends Animal {
    private String dogName;


    /**
     * 子类构造函数必须调用父类构造函数
     */
    public Dog(){
        System.out.println("dog构造方法");
    }
    /**
     * 子类构造函数必须调用父类构造函数
     */
    public Dog (String dogName){
        System.out.println("dog name is " + dogName);
        this.dogName = dogName;
    }

    public String getDogName() {
        return dogName;
    }

    @Override
    public String toString() {
        return super.toString() + "Dog{" +
                "dogName='" + dogName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Dog dog = new Dog("警犬");
    }
}
