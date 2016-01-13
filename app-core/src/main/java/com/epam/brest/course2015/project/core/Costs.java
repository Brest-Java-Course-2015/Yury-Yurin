package com.epam.brest.course2015.project.core;

public class Costs {

    public Costs(Integer id, Integer cost) {
        this.id = id;
        this.cost = cost;
    }
    public Costs() {
    }

    public Costs(Costs costs) {
        this.id = costs.id;
        this.cost = costs.cost;
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
