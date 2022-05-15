package com.gson.pico.container;

import org.junit.Assert;
import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.injectors.SetterInjection;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test0(){
        MutablePicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(ArrayList.class);
        List list = pico.getComponent(ArrayList.class);
    }
    /**
     * 不缓存单例，每次都是通过构造函数重新初始化一个实例
     */
    @Test
    public void test1() {
        MutablePicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(Foo.class);
        pico.addComponent(Bar.class);
        Foo component1 = pico.getComponent(Foo.class);
        Foo component2 = pico.getComponent(Foo.class);
        Assert.assertNotEquals(component1, component2);
    }

    @Test
    public void test2() {
        MutablePicoContainer pico = new DefaultPicoContainer(new Caching());
        pico.addComponent(Foo.class);
        Foo component1 = pico.getComponent(Foo.class);
        Foo component2 = pico.getComponent(Foo.class);
        Assert.assertEquals(component1, component2);
    }

    /**
     * setter注入
     */
    @Test
    public void test3() {
        MutablePicoContainer pico = new DefaultPicoContainer(new SetterInjection());
        pico.addComponent(Apple.class);
        pico.addComponent(Banana.class);
        pico.addComponent(Orange.class);
        pico.addComponent(Pear.class);
        Apple component = pico.getComponent(Apple.class);
        System.out.println(component);
    }

    @Test
    public void test4(){
        MutablePicoContainer pico = new DefaultPicoContainer();
        // 执行无参构造函数
        pico.addComponent(Orange.class);
        Orange component1 = pico.getComponent(Orange.class);
        System.out.println(component1);


    }

    @Test
    public void test5(){
        MutablePicoContainer pico = new DefaultPicoContainer();

        // 执行有参构造函数
        pico.addComponent(new Orange("自由输入的描述橘子的句子"));
        Orange component2 = pico.getComponent(Orange.class);
        System.out.println(component2);

        Orange component3 = pico.getComponent(Orange.class);
        System.out.println(component3);

        System.out.println(component3 == component2);
    }
}
