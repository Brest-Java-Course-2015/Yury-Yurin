package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import com.epam.brest.course2015.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private UserDto userDto = new UserDto();
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{login}/{password}", method = RequestMethod.POST)
    public @ResponseBody Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/userdto", method = RequestMethod.GET)
    public @ResponseBody UserDto getUserDto() {
        return userService.getUserDto();
    }
}
