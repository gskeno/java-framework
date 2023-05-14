package com.gson.mysql.test;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCTest {

    @Test
    public void test() throws ClassNotFoundException, SQLException {
//        Connection con = DriverManager.getConnection("jdbc:mysql://121.5.128.170:3306/gs", "root", "76^8673NGD1368");
//        boolean readOnly = con.isReadOnly();
//        System.out.println(readOnly);
        String classpath = System.getProperty("java.class.path");
        // 类路径下有mysql驱动包
        Assert.assertTrue(classpath.contains("mysql-connector-java-8.0.19.jar"));
        // 会首先执行DriverManager类静态代码块
        Connection con = DriverManager.getConnection("jdbc:mysql://121.5.128.170:3306/gs", "root", "76^8673NGD1368");

        Class.forName("com.mysql.cj.jdbc.Driver", true, JDBCTest.class.getClassLoader());
        Class.forName("com.mysql.cj.jdbc.Driver", true, JDBCTest.class.getClassLoader());
    }
}
