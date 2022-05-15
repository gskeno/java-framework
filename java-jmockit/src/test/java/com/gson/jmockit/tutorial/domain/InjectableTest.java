package com.gson.jmockit.tutorial.domain;

import mockit.Injectable;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class InjectableTest {

    /**
     * 只影响这一个实例
     */
    @Injectable
    Locale locale;

    @Test
    public void test(){
        Assert.assertNull(locale.getCountry());
        // Injectable 不会影响静态方法
        Assert.assertNotNull(Locale.getDefault());

        // Injectable不会影响类的所有实例
        Locale locale1 = new Locale("zh", "CN");
        Assert.assertNotNull(locale1.getCountry());
    }
}
