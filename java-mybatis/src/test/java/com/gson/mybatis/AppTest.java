package com.gson.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
//        SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);
//
//        SqlSession sqlSession = new SqlSessionTemplate(sessionFactory);
//        List<Object> list = sqlSession.selectList("model.user.list", null);
//        System.out.println(list);
//        Object o = sqlSession.selectOne("model.user.query", "3");
//        System.out.println(o);
        test1();
    }

    public static void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = new SqlSessionTemplate(sessionFactory);

        TransactionTemplate template = context.getBean(TransactionTemplate.class);
        Object result = template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Object o = null;
                for (int i = 0; i < 5 ; i++) {
                    String userId = "4";
                    o = sqlSession.selectOne("model.user.query", userId);
                    System.out.println(o);
                    if (o == null){
                        Map<String,Object> map = new HashMap<>();
                        map.put("userId", userId);
                        map.put("userName", userId);
                        int insert = 0;
                        try {
                             insert = sqlSession.insert("model.user.insert", map);
                        }catch (Exception e){
                            System.out.println("写入失败");
                        }
                        if (insert > 0){
                            System.out.println("写入成功");
                        }
                    }

                }
                return o;
            }
        });

    }
}
