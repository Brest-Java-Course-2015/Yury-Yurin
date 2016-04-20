package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.User;

import java.util.List;

public interface UsersDao {
    public Integer addUser(String login, String hash,Integer r,Integer n);
    public User getUser(String login);
    public List<User> getAllUsers();
    public void setNewUserHash(String login, String hash,Integer n);
    public Integer getR(String login);
    public Integer getN(String login);
}
