package com.gson.jmockit.tutorial.domain;

import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpSession;

/**
 * 使用场景，依赖一个外部服务，外部服务只是提供了接口，我们是消费方，提供方还没实现上线，
 * 这里mock接口则不再需等待提供方实现。
 */
public class MockedInterfaceTest {

    /**
     * mock了一个接口，这里返回的是一个代理类
     */
    @Mocked
    HttpSession httpSession;

    @Test
    public void test(){
        Assert.assertNotNull(httpSession);
        // String 被mock为 null
        Assert.assertNull(httpSession.getId());
        // 原始类型long 被mock为0
        Assert.assertEquals(httpSession.getCreationTime(), 0);
        // 内部的对象依然是mock的
        Assert.assertNotNull(httpSession.getServletContext());
        // 封装类型，String都被mock为null
        Assert.assertNull(httpSession.getServletContext().getContextPath());
    }
}
