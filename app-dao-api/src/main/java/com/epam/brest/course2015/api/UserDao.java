package com.epam.brest.course2015.api;

import com.epam.brest.course2015.domain.User;
import java.util.List;
public interface UserDao {
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public void deleteUser(String login);
    public void addUser(Integer id,String login, String password);
}