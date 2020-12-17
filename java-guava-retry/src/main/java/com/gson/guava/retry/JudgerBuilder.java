package com.gson.guava.retry;

public class JudgerBuilder<V> {

    private JudgerBuilder(){

    }

    public static <T> JudgerBuilder<T> newBuilder(){
        return new JudgerBuilder<T>();
    }
}
