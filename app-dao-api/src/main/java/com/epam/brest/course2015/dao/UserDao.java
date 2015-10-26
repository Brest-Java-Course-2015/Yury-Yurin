package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;

import java.util.Date;
import java.util.List;
public interface UserDao {
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public void deleteUser(String login);
    public Integer addUser(Integer id,String login, String password, Date createDate);
    public void updateUser(User user);
    public Integer addUser(User user);

}