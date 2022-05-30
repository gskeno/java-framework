package com.gsonkeno.junit.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 指定一个Runner来执行测试用例，这个Runner叫套件Runner，
 * 即将SuiteTestA,SuiteTestB,这两个测试用例打包成一个套件执行
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SuiteTestA.class, SuiteTestB.class})
public class SuiteTests {

    /**
     * 该测试用例执行时，框架会报初始化错误,
     * java.lang.Exception: No tests found matching Method testA(com.gsonkeno.junit.suite.SuiteTests)
     * from org.junit.internal.requests.ClassRequest
     */
    @Test
    public void testA() {
        System.out.println("我是不会执行的");
    }
}
