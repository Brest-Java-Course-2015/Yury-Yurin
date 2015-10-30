package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import com.epam.brest.course2015.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public
    @ResponseBody
    User getUser(@PathVariable(value = "login") String login) {
        return userService.getUserByLogin(login);
    }
    //  @CrossOrigin
    // @RequestMapping(value = "/user/{login}/{password}", method = RequestMethod.POST)
    //@ResponseStatus(value = HttpStatus.CREATED)
    //public @ResponseBody Integer addUser(@RequestBody User user) {
    //
    //return userService.addUser(user);
    //}

    @CrossOrigin
    @RequestMapping(value = "/user/{login}/{password}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public
    @ResponseBody
    Integer addUser(@PathVariable(value = "login") String login,
                    @PathVariable(value = "password") String password) {
        User user = new User(login, password);
        return userService.addUser(user);
    }

    @CrossOrigin
    @RequestMapping(value = "/user/{login}/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void deleteUser(@PathVariable(value = "login") String login) {
        userService.deleteUser(login);
    }

    @RequestMapping(value = "/userdto", method = RequestMethod.GET)
    public @ResponseBody UserDto getUserDto() {
        return userService.getUserDto();
    }

    @CrossOrigin
    @RequestMapping(value = "/user/{id}/{password}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable(value = "id") Integer id, @PathVariable(value = "password") String password) {
        userService.updateUser(new User(id, password));
    }
}
