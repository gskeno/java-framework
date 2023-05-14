package com.gson.mysql;

import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Connection con = DriverManager.getConnection("jdbc:mysql://121.5.128.170:3306/gs", "root", "76^8673NGD1368");
//        boolean readOnly = con.isReadOnly();
//        System.out.println(readOnly);
        System.out.println(System.getProperty("java.class.path"));
        Class.forName("com.mysql.cj.jdbc.Driver", true, JDBC.class.getClassLoader());
        Class.forName("com.mysql.cj.jdbc.Driver", true, JDBC.class.getClassLoader());
    }
}
