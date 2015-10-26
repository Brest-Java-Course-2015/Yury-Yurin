package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserDaoImpl implements UserDao {

    private RowMapper<User> userMapper = new BeanPropertyRowMapper<User>(User.class);

    private static final Logger LOGGER = LogManager.getLogger();

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    @Value("${user.select}")
    private String userSelect;

    @Value("${user.selectById}")
    private String userSelectById;

    @Value("${user.addUser}")
    private String addUser;

    @Value("${user.deleteUser}")
    private String deleteUser;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<User> getAllUsers() {
        return namedParameterJdbcTemplate.query(userSelect, userMapper);
    }

    @Override
    public User getUserById(Integer id) {
        LOGGER.info("Try: get user by id = : {}", id);
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(userSelectById, hashMap, userMapper);
    }

    @Override
    public void deleteUser(String login) {
        LOGGER.info("Try: delete user whith login: :{}",login);
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        namedParameterJdbcTemplate.update(deleteUser, hashMap);
        LOGGER.info("User :{} was delete", login);
    }

    @Override
    public Integer addUser(Integer id,String login, String password,Date createdDate) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id",id);
        hashMap.put("login",login);
        hashMap.put("password", password);
        namedParameterJdbcTemplate.update(addUser, hashMap);
        return id;
    }

    @Override
    public void updateUser(User user) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id",user.getUserId());
        hashMap.put("login",user.getLogin());
        namedParameterJdbcTemplate.update(addUser,hashMap);
    }
    @Override
    public Integer addUser(User user) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id",user.getUserId());
        hashMap.put("login",user.getLogin());
        hashMap.put("password",user.getPassword());
        namedParameterJdbcTemplate.update(addUser,hashMap);
        return user.getUserId();
    }

}
