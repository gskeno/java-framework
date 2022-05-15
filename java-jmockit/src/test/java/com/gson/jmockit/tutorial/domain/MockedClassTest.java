package com.gson.jmockit.tutorial.domain;

import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * http://jmockit.cn/showArticle.htm?channel=2&id=5
 */
public class MockedClassTest {
    // 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，不用担心它为null
    @Mocked
    Locale locale;

    @Test
    public void test(){
        Locale aDefault = Locale.getDefault();
        // 静态方法不起作用了，返回null
        Assert.assertNull(aDefault);

        // 非静态方法，也不起作用了，返回null
        String country = locale.getCountry();
        Assert.assertNull(country);

        // 自己new一个对象，同样被mock了
        Locale locale1 = new Locale("zh", "CN");
        Assert.assertNull(locale1.getCountry());
    }
}
