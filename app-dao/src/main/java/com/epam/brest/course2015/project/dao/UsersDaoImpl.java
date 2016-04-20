package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    @Value("${users.addUser}")
    private String addUser;

    @Value("${users.getAllUsers}")
    private String getAllUsers;

    @Value("${users.getUser}")
    private String getUser;

    @Value("${users.getId}")
    private String getId;

    @Value("${users.setNewHash}")
    private String setNewHash;

    @Value("${users.getR}")
    private String getR;

    @Value("${users.getN}")
    private String getN;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<User>(User.class);

    public UsersDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addUser(String login, String hash, Integer r, Integer n) {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("login",login);
        hashMap.put("hash",hash);
        hashMap.put("salt", r);
        hashMap.put("n", n);
        namedParameterJdbcTemplate.update(addUser, hashMap);
        return namedParameterJdbcTemplate.queryForObject(getId,hashMap,Integer.class);
    }

    @Override
    public User getUser(String login) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        return (User)namedParameterJdbcTemplate.queryForObject(getUser, hashMap, rowMapper);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)namedParameterJdbcTemplate.query(getAllUsers, rowMapper);
    }

    @Override
    public void setNewUserHash(String login, String hash, Integer n) {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("login", login);
        hashMap.put("hash", hash);
        hashMap.put("n",n);
        namedParameterJdbcTemplate.update(setNewHash,hashMap);
    }

    @Override
    public Integer getR(String login) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        return namedParameterJdbcTemplate.queryForObject(getR,hashMap,Integer.class);
    }

    @Override
    public Integer getN(String login) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("login", login);
        return namedParameterJdbcTemplate.queryForObject(getN,hashMap,Integer.class);
    }
}
