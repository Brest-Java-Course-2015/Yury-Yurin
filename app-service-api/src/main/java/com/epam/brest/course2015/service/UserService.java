package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public Integer addUser(User user);
    public void logUser(User User);
    public User getUserByLogin(String login);
    public UserDto getUserDto();
    public void updateUser(User user);
    public void deleteUser(String login);
}
