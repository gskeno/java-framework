package com.gson.javajdk.concurrent;

import com.gson.javajdk.clazz.Dog;

public class InheritableThreadLocalDemo3 {
    private static InheritableThreadLocal<Dog> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog("警犬");
        threadLocal.set(dog);
        System.out.println("value:"+threadLocal.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Dog value = threadLocal.get();
                System.out.println("value:"+value);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("value:"+threadLocal.get());
            }
        });
        thread.start();
        Thread.sleep(1000);
        dog.setDogName("柴犬");
        threadLocal.set(dog);
        Thread.sleep(20000);
    }
}
