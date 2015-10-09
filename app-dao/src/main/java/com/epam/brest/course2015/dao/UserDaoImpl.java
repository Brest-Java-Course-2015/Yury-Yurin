package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.dao.rowmappers.UserMapper;
import com.epam.brest.course2015.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    private UserMapper userMapper = new UserMapper();

    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", userMapper);
    }

    @Override
    public User getUserById(Integer id) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        return jdbcTemplate.queryForObject("select * from user where userid=:id", hashMap, userMapper);
    }

    @Override
    public void deleteUser(String login) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        jdbcTemplate.update("delete from user where login=:login", hashMap);
    }

    @Override
    public void addUser(Integer id,String login, String password) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id",id);
        hashMap.put("login",login);
        hashMap.put("password",password);
        jdbcTemplate.update("insert into user(userId,login,password) values(:id,:login,:password)",hashMap);

    }
}
