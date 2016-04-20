package com.epam.brest.course2015.project.core;

public class User {

    public User() {

    }

    public User(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.hash = user.getHash();
        this.salt = user.getSalt();
    }

    public User(Integer userId, String login, String hash, Integer salt) {
        this.userId = userId;
        this.login = login;
        this.hash = hash;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;
    private String login;
    private String hash;
    private Integer salt;

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    private Integer n;
}
