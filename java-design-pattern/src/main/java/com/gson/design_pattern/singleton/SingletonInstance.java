package com.gsonkeno.pattern.singleton;

/**
 * 双重加锁机制创建单例
 * 但是不能阻止通过反射重复创建对象
 * Created by gaosong on 2017-12-08
 */
public class SingletonInstance{
    private static volatile SingletonInstance instance;

    private SingletonInstance(){
    }

    public static SingletonInstance getSingleton(){
        if (instance == null){
            synchronized (SingletonInstance.class){
                if (instance == null){
                    instance = new SingletonInstance();
                }
            }
        }
        return instance;
    }
}
