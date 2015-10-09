package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.dao.rowmappers.UserMapper;
import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private UserMapper userMapper = new UserMapper();

    private static final Logger LOGGER = LogManager.getLogger();

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${user.select}")
    private String userSelect;

    @Value("${user.selectById}")
    private String userSelectById;

    @Value("${user.addUser}")
    private String addUser;

    @Value("${user.deleteUser}")
    private String deleteUser;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(userSelect, userMapper);
    }

    @Override
    public User getUserById(Integer id) {
        LOGGER.info("id: {}", id);
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        return jdbcTemplate.queryForObject(userSelectById, hashMap, userMapper);
    }

    @Override
    public void deleteUser(String login) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        jdbcTemplate.update(deleteUser, hashMap);
    }

    @Override
    public void addUser(Integer id,String login, String password) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id",id);
        hashMap.put("login",login);
        hashMap.put("password",password);
        jdbcTemplate.update(addUser,hashMap);
    }
}
