package com.gson.java8.method_reference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    /**
     * 方法引用永远是可以  转化为  lamda表达式的
     * @param args
     */
    public static void main(String[] args) {
        Supplier<Product> supplier = Product::new;
        Product product1 = supplier.get();
        Product product2 = supplier.get();
        List<Product> products = Arrays.asList(product1, product2);

        // 这叫做方法引用
        products.forEach(System.out::println);
        // 这叫做lamda表达式，与上面是等价的
        products.forEach(x -> System.out.println(x));

        // 以下两者等价
        products.forEach(product1::getPrice);
        products.forEach(product3 -> product1.getPrice(product3));


        //以下两者等价
        products.forEach(Product::repair);
        products.forEach(product -> product.repair());

        // 以下两者等价，是不可编译的
        // products.forEach(Product::getPrice);
        // products.forEach(product -> Product.getPrice(product));

    }
}
