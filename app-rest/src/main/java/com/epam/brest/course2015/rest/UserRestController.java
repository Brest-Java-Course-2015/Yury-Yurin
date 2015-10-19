package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{login}/{password}", method = RequestMethod.POST)
    public @ResponseBody Integer addUser(@PathVariable(value = "login") String login,
                                         @PathVariable(value = "password") String password) {
        Date date = new Date();
        Integer id=5;
        return userService.addUser(new User(id,login,password,date));
    }
}