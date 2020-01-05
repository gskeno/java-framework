package com.gson.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);

        SqlSession sqlSession = new SqlSessionTemplate(sessionFactory);
        List<Object> list = sqlSession.selectList("model.user.list", null);
        System.out.println(list);
    }
}
