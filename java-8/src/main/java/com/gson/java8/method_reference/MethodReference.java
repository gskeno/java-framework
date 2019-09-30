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

        System.out.println("product1 price is " + product1.price);
        System.out.println("product2 price is " + product2.price);

        // 这叫做方法引用
        products.forEach(System.out::println);
        // 这叫做lamda表达式，与上面是等价的
        products.forEach(x -> System.out.println(x));

        // 以下三者等价,forEach方法需要传人一个Consumer对象
        // product1::getPrice 只是把products集合中的两个元素作为getPrice的入参
        // product2::getPrice 也是一样
        // product3 -> product1.getPrice(product3)中的product3只是入参的一个符号而已，
        // 改成product4，product5都是一样的。
        products.forEach(product1::getPrice);
        System.out.println("------1------");
        products.forEach(product2::getPrice);
        System.out.println("------2------");
        products.forEach(product4 -> product1.getPrice(product4));
        System.out.println("------3------");

        //以下两者等价
        products.forEach(Product::repair);
        products.forEach(product -> product.repair());

        // 以下两者等价，是不可编译的
        // products.forEach(Product::getPrice);
        // products.forEach(product -> Product.getPrice(product));

    }
}
