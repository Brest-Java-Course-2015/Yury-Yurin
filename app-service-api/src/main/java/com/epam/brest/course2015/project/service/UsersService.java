package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.User;

import java.util.List;

public interface UsersService {
    public Integer addUser(String login, String hash);
    public Boolean checkUser(String login, String hash);
    public List<User> getAllUsers();
    public Integer generateR();
    public Integer getN(String login);
    public Integer getNewN();
    public Integer getR(String login);
}
