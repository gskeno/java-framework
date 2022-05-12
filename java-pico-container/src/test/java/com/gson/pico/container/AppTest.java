package com.gson.pico.container;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.injectors.SetterInjection;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * 不缓存单例，每次都是通过构造函数重新初始化一个实例
     */
    @Test
    public void test1(){
       MutablePicoContainer pico = new DefaultPicoContainer();
       pico.addComponent(Foo.class);
       pico.addComponent(Bar.class);
       Foo component1 = pico.getComponent(Foo.class);
       Foo component2 = pico.getComponent(Foo.class);
       Assert.assertNotEquals(component1, component2);
   }

   @Test
    public void test2(){
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
    public void test3(){
        MutablePicoContainer pico = new DefaultPicoContainer(new SetterInjection());
        pico.addComponent(Banana.class);
        pico.addComponent(Orange.class);
        pico.addComponent(Pear.class);
        pico.addComponent(Apple.class);
        Apple component = pico.getComponent(Apple.class);
        System.out.println(component);
   }
}
