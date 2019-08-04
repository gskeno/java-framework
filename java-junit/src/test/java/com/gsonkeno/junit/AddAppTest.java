package com.gsonkeno.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * junit 测试 Paramterized使用方式
 * https://wiki.jikexueyuan.com/project/junit/parameterized-test.html
 *
 * @RunWith(Parameterized.class)
 */
@RunWith(Parameterized.class)
public class AddAppTest extends AppTest {

    private final String arg1;
    private final String arg2;

    // 如果该类没有一个静态方法时，会报如下异常
    // java.lang.Exception: No public static parameters method on class com.gsonkeno.junit.AddAppTest
    // java.lang.Exception: com.gsonkeno.junit.AddAppTest.buildData() must return an Iterable of arrays.

    // 构造多组成员变量，用于测试用例，可以让测试用例执行多次，每次的测试数据都可以不一样
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> buildData(){
        List<Object[]> data = getData();
        return data;
    }

    public AddAppTest(String arg1, String arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Test
    public void testAdd(){
        System.out.println("AddAppTest class testAdd method");
        System.out.println("arg1 = " + arg1 + ", arg2 = " + arg2);
    }
}
