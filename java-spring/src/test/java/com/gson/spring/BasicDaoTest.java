package com.gson.spring;

import com.gson.spring.inner.BasicDao;
import com.gson.spring.inner.DaoContext;
import com.gson.spring.inner.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasicDaoTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
                ("classpath*:parent-test.xml");
        //注释的两行如果放开，肯定会抛出异常
        //BasicDao basicDao = context.getBean(BasicDao.class);
        //System.out.println(basicDao);
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println(userDao);
        DaoContext daoContext = context.getBean(DaoContext.class);
        System.out.println(daoContext);
        System.out.println(daoContext.getBasicDao());
    }
}
