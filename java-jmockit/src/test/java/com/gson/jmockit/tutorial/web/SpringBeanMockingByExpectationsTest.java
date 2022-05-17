package com.gson.jmockit.tutorial.web;

import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration(locations = {"/META-INF/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBeanMockingByExpectationsTest {
    // 注入Spring Bean，Mock这个实例，就达到了Mock Spring Bean的目的
    @Resource
    WebOrdinaryBean webOrdinaryBean;

    @Test
    public void testSpringBeanMockingByExpectation() {
        // 直接把实例传给Expectations的构造函数即可Mock这个实例
        new Expectations(webOrdinaryBean) {
            {
                webOrdinaryBean.finalMethod();
                result = "testFinal";
            }
        };
        Assert.assertEquals(webOrdinaryBean.finalMethod(), "testFinal");
    }
}
