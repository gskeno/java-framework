package com.gsonkeno.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by gaosong on 2018-04-04
 */
public class UserServiceImpl implements UserService {

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
    }

    @Override
    public List<User> getUsers() {
        List<User> list = jdbcTemplate.query("select * from user", new UserRowMapper());
        return list;
    }
}
