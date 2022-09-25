package com.gsonkeno.spring.test;

import com.gsonkeno.spring.bean.replace_method.TestChangeMethod;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * Created by gaosong on 2018-04-03
 */
public class ReplaceMethodTest  {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("ReplaceMethodTest.xml"));
        TestChangeMethod testChangeMethod = (TestChangeMethod)bf.getBean("testChangeMethod");
        //changeMe原有的方法被替换为TestMethodReplacer中的reimplement方法
        testChangeMethod.changeMe();
    }
}
