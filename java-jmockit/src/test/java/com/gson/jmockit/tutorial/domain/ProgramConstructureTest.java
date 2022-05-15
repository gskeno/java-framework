package com.gson.jmockit.tutorial.domain;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Assert;
import org.junit.Test;

/**
 * http://jmockit.cn/showArticle.htm?channel=2&id=4
 */
public class ProgramConstructureTest {

    /**
     * 测试属性
     */
    @Mocked
    HelloJmockit helloJmockit;

    @Test
    public void test1(){
        // 录制(Record)
        new Expectations(){
            {
                helloJmockit.sayHello();
                // 表示上方调用sayHello时，返回的是这里录制的"hello, david"
                result = "hello, david";
            }
        };

        // 重放(Reply)
        String helloMsg = helloJmockit.sayHello();
        Assert.assertEquals(helloMsg, "hello, david");

        // 验证(Verify)
        new Verifications(){
            {
                helloJmockit.sayHello();
                // sayHello执行了一次
                times = 1;
            }
        };
        helloJmockit.sayHello();
        new Verifications(){
            {
                helloJmockit.sayHello();
                // sayHello执行了2次
                times = 2;
            }
        };
    }

    /**
     * @param helloJmockit 这是一次测试参数
     */
    @Test
    public void test1(@Mocked HelloJmockit helloJmockit){
        // 录制(Record)
        new Expectations(){
            {
                helloJmockit.sayHello();
                // 表示上方调用sayHello时，返回的是这里录制的"hello, david"
                result = "hello, david";
            }
        };

        // 重放(Reply)
        String helloMsg = helloJmockit.sayHello();
        Assert.assertEquals(helloMsg, "hello, david");

        // 验证(Verify)
        new Verifications(){
            {
                helloJmockit.sayHello();
                // sayHello执行了一次
                times = 1;
            }
        };
    }






}
