package com.gsonkeno.spring.test;

import com.gsonkeno.spring.bean.GetBeanTest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by gaosong on 2018-04-03
 */
public class LookUpMethodTest {

    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("LookUpTest.xml"));
        GetBeanTest getBeanTest = (GetBeanTest)bf.getBean("getBeanTest");
        getBeanTest.showMe();
        System.out.println(getBeanTest.getClass().getCanonicalName());
    }
}
