package com.gsonkeno.spring.jdbc.transaction;

import com.gsonkeno.spring.jdbc.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by gaosong on 2018-04-04
 */
public class UserServiceImpl1 implements UserService1 {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.update("insert into user(name,age,sex) values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()}, new int[]{
                Types.VARCHAR,Types.INTEGER,Types.VARCHAR
                });
        //throw new RuntimeException("aa");
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE USER SET name = ?, age = ?, sex = ? where id = ?",
                new Object[]{user.getName(), user.getAge(), user.getSex(), user.getId()}, new int[]{
                        Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER
                });

        System.out.println("mm");
    }

}
