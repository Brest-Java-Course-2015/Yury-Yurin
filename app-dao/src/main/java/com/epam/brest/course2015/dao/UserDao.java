package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import java.util.List;
/**
 * Created by blondeks on 7.10.15.
 */
public interface UserDao {
    public List<User> getAllUsers();
    public User getUserById();
}
