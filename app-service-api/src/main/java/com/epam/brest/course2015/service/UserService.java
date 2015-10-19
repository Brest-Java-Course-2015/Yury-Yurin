package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public Integer addUser(User user);
}
