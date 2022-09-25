package com.gson.spring.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by gaosong on 2018-04-03
 */
public class LookUpMethodTest {


    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("LookUpTest.xml"));
        LookUpMainBean lookUpMainBean = (LookUpMainBean)bf.getBean("lookUpMainBean");
        lookUpMainBean.showMe();
        System.out.println(lookUpMainBean.getClass().getCanonicalName());
        System.out.println(lookUpMainBean.getUser());
        System.out.println(lookUpMainBean.getUser().getClass().getCanonicalName());

        System.out.println(lookUpMainBean.getUser());
        System.out.println(lookUpMainBean.getUser().getClass().getCanonicalName());
    }
}
