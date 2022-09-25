package com.gsonkeno.jdk.training.jvm;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gaosong on 2017-12-21
 */
public class JdbcUtils {

    static {
        try {
            // (1)
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url) throws SQLException {
        // (2)
        System.out.println(Driver.class.getClassLoader().getClass());
        return DriverManager.getConnection(url);
    }
}

