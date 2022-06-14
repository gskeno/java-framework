package com.gson.pico.container;

import com.gson.pico.container.introduce.*;
import org.junit.Assert;
import org.junit.Test;
import org.picocontainer.Characteristics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.containers.TransientPicoContainer;
import org.picocontainer.injectors.*;

import java.lang.reflect.Method;
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

    @Test
    public void  testCache1(){
        MutablePicoContainer pico = new DefaultPicoContainer();
        // 一次性，所以as与addComponent要一起使用
        pico.as(Characteristics.CACHE).addComponent(Foo.class);
        Foo foo1 = pico.getComponent(Foo.class);
        Foo foo2 = pico.getComponent(Foo.class);
        assert foo1 == foo2;

        // 单例模式不再生效
        pico.addComponent(Bar.class);
        Bar bar1 = pico.getComponent(Bar.class);
        Bar bar2 = pico.getComponent(Bar.class);
        assert bar1 != bar2;
    }

    @Test
    public void testConstructorInject(){
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(Water.class);
        pico.addComponent(Vegetable.class);
        pico.addComponent(Beef.class);
        pico.addComponent(Pig.class);
        pico.addComponent(Food.class);

        Food food = pico.getComponent(Food.class);
        System.out.println(food);
    }

    @Test
    public void testSetterInject(){
        DefaultPicoContainer pico = new DefaultPicoContainer(new SetterInjection());
        pico.addComponent(Food.class);
        pico.addComponent(Water.class);
        pico.addComponent(Beef.class);
        pico.addComponent(Pig.class);
        pico.addComponent(Vegetable.class);

        pico.getComponent(Food.class);
    }

    @Test
    public void testSetterInject1(){
        DefaultPicoContainer pico = new DefaultPicoContainer(new SetterInjection("mySynonymForSet"));
        pico.addComponent(City.class);
        pico.addComponent(House.class);

        pico.getComponent(City.class);
    }

    @Test
    public void testAnnotatedFieldInjection(){
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(Spring.class);
        pico.addComponent(Flower.class);

        Spring component = pico.getComponent(Spring.class);
        System.out.println(component);
    }

    @Test
    public void testAnnotatedMethodInjection(){
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(Summer.class);
        pico.addComponent(RainStorm.class);

        Summer component = pico.getComponent(Summer.class);
        System.out.println(component);
    }

    @Test
    public void testTypedFieldInjection(){
        DefaultPicoContainer pico = new DefaultPicoContainer(new TypedFieldInjection());
        pico.addComponent(River.class);
        pico.addComponent(Leaf.class);
        // 将会使用无参构造函数初始化Autumn实例
        pico.as(TypedFieldInjection.injectionFieldTypes(River.class.getName(),
                Leaf.class.getName())).addComponent(Autumn.class);

        Autumn component = pico.getComponent(Autumn.class);
        System.out.println(component);
    }

    @Test
    public void testNamedMethodInjection(){
        MutablePicoContainer pico = new DefaultPicoContainer(new NamedMethodInjection());
        pico.addConfig("a", new Snow());
        pico.addConfig("b", new ColdWind());
        pico.addConfig("c", new HeaveySnow());
        pico.addComponent(Winter.class);

        Winter component = pico.getComponent(Winter.class);
        System.out.println("winter:" + component);

        Snow snow = (Snow)pico.getComponent("a");
        System.out.println("a:" + snow);

        Snow heavySnow = (Snow)pico.getComponent("c");
        System.out.println("c:" + heavySnow);
    }

    @Test
    public void testRejection1() throws NoSuchMethodException {
        MutablePicoContainer pico = new TransientPicoContainer();
        pico.addComponent(User.class);
        pico.addComponent(Store.class);
        pico.addComponent(ShoppingCart.class);
        pico.addComponent(Make.class, new Make());
        pico.addComponent(Model.class, new Model());
        int a = 18;
        pico.addComponent(Integer.class, a);
        // 每次生成组件实例时，都会执行指定的后置方法
        new Reinjector(pico).reinject(ShoppingCart.class,
                ShoppingCart.class.getMethod("addItemTo", Make.class, Model.class, int.class ));
        System.out.println("------");
        ShoppingCart component1 = pico.getComponent(ShoppingCart.class);
        ShoppingCart component2 = pico.getComponent(ShoppingCart.class);
    }

    @Test
    public void testRejection2() throws NoSuchMethodException {
        MutablePicoContainer parent = new DefaultPicoContainer();
        parent.addComponent(User.class);
        parent.addComponent(Store.class);
        parent.addComponent(ShoppingCart.class);
        parent.addComponent(Make.class, new Make());
        parent.addComponent(Model.class, new Model());
        parent.addComponent(Integer.class, 18);

        Method addItemTo = ShoppingCart.class.getMethod("addItemTo", Make.class, Model.class, int.class);
        MethodInjection methodInjection = new MethodInjection(addItemTo);
        Reinjection reinjection = new Reinjection(methodInjection, parent);
        MutablePicoContainer pico = new TransientPicoContainer(reinjection, parent);

        ShoppingCart component1 = pico.getComponent(ShoppingCart.class);
        System.out.println(component1);
    }

    @Test
    public void testProviderInjection(){
        MutablePicoContainer pico = new DefaultPicoContainer();
        // 根据巧克力豆生产巧克力
        pico.addAdapter(new ProviderAdapter(new Chocolatier()));
        // 巧克力豆
        pico.addComponent(CocaoBeans.class);
        pico.addComponent(NeedsChocolate.class);

        pico.getComponent(NeedsChocolate.class);
    }

    @Test
    public void testAmbiguousNames(){
        MutablePicoContainer pico = new DefaultPicoContainer(new SetterInjection());
        pico.addComponent("house1", new House("别墅"));
        pico.addComponent("house2", new House("大平层"));

        try {
            // 构造函数的参数名是house
            pico.as(Characteristics.USE_NAMES).addComponent(City.class);
            City component = pico.getComponent(City.class);
            System.out.println(component);
            throw new RuntimeException("not should go here");
        }catch (AbstractInjector.AmbiguousComponentResolutionException e){
            assert e.getMessage().contains("but there are too many choices to inject");
        }

        // 执行构造函数
        pico.addComponent("house", new House("商品房"));
        City component = pico.getComponent(City.class);

    }



}
