package com.gson.java8.method_reference;

import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    /**
     * 方法引用，静态方法中，只能引用静态方法
     * @param args
     */
    public static void main(String[] args) {
        Function<Integer, ?> func = Product::get;
        Supplier<Product> supplier = Product::new;
    }
}
