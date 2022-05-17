package com.gson.jmockit.tutorial.web;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration(locations = {"/META-INF/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBeanMockingByMockUpTest {
    @Resource
    private WebOrdinaryBean webOrdinaryBean;

    @Test
    public void test(){
        new MockUp<WebOrdinaryBean>(WebOrdinaryBean.class){
            @Mock
            private String privateMethod(){
                return "private mockup";
            }
        };

        Assert.assertEquals(webOrdinaryBean.getPrivateMethodReturnVal(), "private mockup");
    }
}
