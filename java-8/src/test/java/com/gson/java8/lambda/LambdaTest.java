package com.gson.java8.lambda;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.util.function.Consumer;

public class LambdaTest {

    /**
     * 方法引用就是方法的引用而不是方法的执行
     */
    @Test
    public void test() {
        Consumer<String> consumer = String::toString;
    }
}
