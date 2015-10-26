package com.epam.brest.course2015.dto;

import com.epam.brest.course2015.domain.User;

import java.util.List;

public class UserDto {
    private List<User> users;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private int total;
}
