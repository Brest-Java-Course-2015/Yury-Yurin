package com.epam.brest.course2015.project.core;

public class ApplicationCosts {

    public ApplicationCosts(Integer id, Integer cost) {
        this.id = id;
        this.cost = cost;
    }
    public ApplicationCosts() {
    }

    public ApplicationCosts(ApplicationCosts applicationCosts) {
        this.id = applicationCosts.id;
        this.cost = applicationCosts.cost;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    Integer id;
    Integer cost;
}
