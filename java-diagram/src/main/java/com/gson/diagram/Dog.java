package com.gson.diagram;

public class Dog<R extends Food> extends Animal{
    private Food food;

    public Dog(Food food){
     this.food = food;
    }
    @Override
    public void enjoy(Food food) {
        if (food instanceof Bone){
            System.out.println("狗狗享受骨头");
        }else {
            System.out.println("不是骨头我不爱");
        }
    }
}
