package com.gson.guava;

public class MathService {

    <T extends Number> int add(T a, T b){
        return a.intValue() + b.intValue();
    }

    public <T extends Number> int minus(T a, T b){
        return a.intValue() - b.intValue();
    }

    private  <T extends Number> int multi(T a, T b){
        return a.intValue() * b.intValue();
    }

    protected <T extends Number> double divide(T a, T b){
        return a.doubleValue() /  b.doubleValue();
    }

    public  <T>  void operate(T t){
        System.out.println(t);
    }

    public  <T extends Number>  T ops(T t){
        System.out.println(t);
        return null;
    }
}
